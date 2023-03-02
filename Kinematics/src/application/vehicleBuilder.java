package application;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javafx.event.EventHandler;
import javafx.scene.DepthTest;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;

public class vehicleBuilder extends Group {

	Group group = new Group();
	SubScene subScene;
	final Group root = new Group();
    final Xform axisGroup = new Xform();
    final Xform moleculeGroup = new Xform();
    final Xform frontSuspension = new Xform();
    final Xform rearSuspension = new Xform();
    final Xform world = new Xform();
    final Xform vehicle = new Xform();
    final PerspectiveCamera camera = new PerspectiveCamera(true);
    final Xform cameraXform = new Xform();
    final Xform cameraXform2 = new Xform();
    final Xform cameraXform3 = new Xform();
    private static final double CAMERA_INITIAL_DISTANCE = -450;
    private static final double CAMERA_INITIAL_X_ANGLE = 70.0;
    private static final double CAMERA_INITIAL_Y_ANGLE = 320.0;
    private static final double CAMERA_NEAR_CLIP = 0.1;
    private static final double CAMERA_FAR_CLIP = 10000.0;
    private static final double AXIS_LENGTH = 250.0;
    private static final double HYDROGEN_ANGLE = 104.5;
    private static final double CONTROL_MULTIPLIER = 0.1;
    private static final double SHIFT_MULTIPLIER = 10.0;
    private static final double MOUSE_SPEED = 0.1;
    private static final double ROTATION_SPEED = 2.0;
    private static final double TRACK_SPEED = 0.3;

    double mousePosX;
    double mousePosY;
    double mouseOldX;
    double mouseOldY;
    double mouseDeltaX;
    double mouseDeltaY;
    
    //Probably still need, or refactor
    private void buildCamera() {
        System.out.println("buildCamera()");
        root.getChildren().add(cameraXform);
        cameraXform.getChildren().add(cameraXform2);
        cameraXform2.getChildren().add(cameraXform3);
        cameraXform3.getChildren().add(camera);
        cameraXform3.setRotateZ(180.0);

        camera.setNearClip(CAMERA_NEAR_CLIP);
        camera.setFarClip(CAMERA_FAR_CLIP);
        camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
        cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
        cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
    }
    
    //Doubt this is needed
    private void buildAxes() {
        System.out.println("buildAxes()");
        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.DARKRED);
        redMaterial.setSpecularColor(Color.RED);

        final PhongMaterial greenMaterial = new PhongMaterial();
        greenMaterial.setDiffuseColor(Color.DARKGREEN);
        greenMaterial.setSpecularColor(Color.GREEN);

        final PhongMaterial blueMaterial = new PhongMaterial();
        blueMaterial.setDiffuseColor(Color.DARKBLUE);
        blueMaterial.setSpecularColor(Color.BLUE);

        final Box xAxis = new Box(AXIS_LENGTH, 1, 1);
        final Box yAxis = new Box(1, AXIS_LENGTH, 1);
        final Box zAxis = new Box(1, 1, AXIS_LENGTH);

        xAxis.setMaterial(redMaterial);
        yAxis.setMaterial(greenMaterial);
        zAxis.setMaterial(blueMaterial);

        axisGroup.getChildren().addAll(xAxis, yAxis, zAxis);
        axisGroup.setVisible(true);
        vehicle.getChildren().addAll(axisGroup);
    }
    
    //Unsure about these
    private void handleMouse(SubScene subScene, final Node root) {
        subScene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent me) {
                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseOldX = me.getSceneX();
                mouseOldY = me.getSceneY();
            }
        });
        subScene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent me) {
                mouseOldX = mousePosX;
                mouseOldY = mousePosY;
                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseDeltaX = (mousePosX - mouseOldX);
                mouseDeltaY = (mousePosY - mouseOldY);

                double modifier = 1.0;

                if (me.isControlDown()) {
                    modifier = CONTROL_MULTIPLIER;
                }
                if (me.isShiftDown()) {
                    modifier = SHIFT_MULTIPLIER;
                }
                if (me.isPrimaryButtonDown()) {
                    cameraXform.ry.setAngle(cameraXform.ry.getAngle() - mouseDeltaX*MOUSE_SPEED*modifier*ROTATION_SPEED);
                    cameraXform.rx.setAngle(cameraXform.rx.getAngle() + mouseDeltaY*MOUSE_SPEED*modifier*ROTATION_SPEED);
                }
                else if (me.isSecondaryButtonDown()) {
                    double z = camera.getTranslateZ();
                    double newZ = z + mouseDeltaX*MOUSE_SPEED*modifier;
                    camera.setTranslateZ(newZ);
                }
                else if (me.isMiddleButtonDown()) {
                    cameraXform2.t.setX(cameraXform2.t.getX() + mouseDeltaX*MOUSE_SPEED*modifier*TRACK_SPEED);
                    cameraXform2.t.setY(cameraXform2.t.getY() + mouseDeltaY*MOUSE_SPEED*modifier*TRACK_SPEED);
                }
            }
        });
    }
    
    //Unsure about these
    private  void handleKeyboard(SubScene subScene, final Node root) {
        subScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case Z:
                        cameraXform2.t.setX(0.0);
                        cameraXform2.t.setY(0.0);
                        camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
                        cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
                        cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
                        break;
                    case X:
                        axisGroup.setVisible(!axisGroup.isVisible());
                        break;
                    case V:
                        moleculeGroup.setVisible(!moleculeGroup.isVisible());
                        break;
                }
            }
        });
    }

    private void frontLinks() {
    	
    	Cylinder cylinder1 = new Cylinder(1,30);
    	Sphere sphere11 = new Sphere(2);
    	sphere11.setTranslateY(15);
    	Sphere sphere21 = new Sphere(2);
    	sphere21.setTranslateY(-15);
    	
    	Xform frontLink1 = new Xform();
    	frontLink1.getChildren().add(sphere11);
    	frontLink1.getChildren().add(sphere21);
    	frontLink1.getChildren().add(cylinder1);
    	//frontLink1.setTranslate(-15,-15,15);
    	
    	Cylinder cylinder2 = new Cylinder(1,30);
    	Sphere sphere12 = new Sphere(2);
    	sphere12.setTranslateY(15);
    	Sphere sphere22 = new Sphere(2);
    	sphere22.setTranslateY(-15);
    	
    	Xform frontLink2 = new Xform();
    	frontLink2.getChildren().add(sphere12);
    	frontLink2.getChildren().add(sphere22);
    	frontLink2.getChildren().add(cylinder2);
    	//frontLink2.setTranslate(15,-15,15);
    	
    	Cylinder cylinder3 = new Cylinder(1,30);
    	Sphere sphere13 = new Sphere(2);
    	sphere13.setTranslateY(15);
    	Sphere sphere23 = new Sphere(2);
    	sphere23.setTranslateY(-15);
    	
    	Xform frontLink3 = new Xform();
    	frontLink3.getChildren().add(sphere13);
    	frontLink3.getChildren().add(sphere23);
    	frontLink3.getChildren().add(cylinder3);
    	//frontLink3.setTranslate(15,-15,-15);
    	
    	Cylinder cylinder4 = new Cylinder(1,30);
    	Sphere sphere14 = new Sphere(2);
    	sphere14.setTranslateY(15);
    	Sphere sphere24 = new Sphere(2);
    	sphere24.setTranslateY(-15);
    	
    	Xform frontLink4 = new Xform();
    	frontLink4.getChildren().add(sphere14);
    	frontLink4.getChildren().add(sphere24);
    	frontLink4.getChildren().add(cylinder4);
    	//frontLink4.setTranslate(15,15,-15);
    	
    	Cylinder cylinder5 = new Cylinder(1,30);
    	Sphere sphere15 = new Sphere(2);
    	sphere15.setTranslateY(15);
    	Sphere sphere25 = new Sphere(2);
    	sphere25.setTranslateY(-15);
    	
    	Xform frontLink5 = new Xform();
    	frontLink5.getChildren().add(sphere15);
    	frontLink5.getChildren().add(sphere25);
    	frontLink5.getChildren().add(cylinder5);
    	//frontLink5.setTranslate(-15,-15,-15);
    	
    	frontSuspension.getChildren().add(frontLink1);
    	frontSuspension.getChildren().add(frontLink2);
    	frontSuspension.getChildren().add(frontLink3);
    	frontSuspension.getChildren().add(frontLink4);
    	frontSuspension.getChildren().add(frontLink5);
    	vehicle.getChildren().addAll(frontSuspension);
    }
    //Doubt this is needed
    private void buildMolecule() {

        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.DARKRED);
        redMaterial.setSpecularColor(Color.RED);

        final PhongMaterial blueMaterial = new PhongMaterial();
        blueMaterial.setDiffuseColor(Color.DARKBLUE);
        blueMaterial.setSpecularColor(Color.BLUE);

        final PhongMaterial whiteMaterial = new PhongMaterial();
        whiteMaterial.setDiffuseColor(Color.LIGHTSKYBLUE);
        whiteMaterial.setSpecularColor(Color.LIGHTBLUE);

        final PhongMaterial greyMaterial = new PhongMaterial();
        greyMaterial.setDiffuseColor(Color.DARKGREY);
        greyMaterial.setSpecularColor(Color.GREY);

        Xform moleculeXform = new Xform();
        Xform oxygenXform = new Xform();
        Xform hydrogen1SideXform = new Xform();
        Xform hydrogen1Xform = new Xform();
        Xform hydrogen2SideXform = new Xform();
        Xform hydrogen2Xform = new Xform();

        Sphere oxygenSphere = new Sphere(40.0);
        oxygenSphere.setMaterial(redMaterial);

        Box hydrogen1Sphere = new Box(30.0,30.0,30.0);
        hydrogen1Sphere.setMaterial(whiteMaterial);
        hydrogen1Sphere.setTranslateX(0.0);

        Sphere hydrogen2Sphere = new Sphere(30.0);
        hydrogen2Sphere.setMaterial(blueMaterial);
        hydrogen2Sphere.setTranslateZ(0.0);

        Cylinder bond1Cylinder = new Cylinder(5, 100);
        bond1Cylinder.setMaterial(blueMaterial);
        bond1Cylinder.setTranslateX(50.0);
        bond1Cylinder.setRotationAxis(Rotate.Z_AXIS);
        bond1Cylinder.setRotate(90.0);

        Cylinder bond2Cylinder = new Cylinder(5, 100);
        bond2Cylinder.setMaterial(greyMaterial);
        bond2Cylinder.setTranslateX(50.0);
        bond2Cylinder.setRotationAxis(Rotate.Z_AXIS);
        bond2Cylinder.setRotate(90.0);

        moleculeXform.getChildren().add(oxygenXform);
        moleculeXform.getChildren().add(hydrogen1SideXform);
        moleculeXform.getChildren().add(hydrogen2SideXform);
        oxygenXform.getChildren().add(oxygenSphere);
        hydrogen1SideXform.getChildren().add(hydrogen1Xform);
        hydrogen2SideXform.getChildren().add(hydrogen2Xform);
        hydrogen1Xform.getChildren().add(hydrogen1Sphere);
        hydrogen2Xform.getChildren().add(hydrogen2Sphere);
        hydrogen1SideXform.getChildren().add(bond1Cylinder);
        hydrogen2SideXform.getChildren().add(bond2Cylinder);

        hydrogen1Xform.setTx(100.0);
        hydrogen2Xform.setTx(100.0);
        hydrogen2SideXform.setRotateY(HYDROGEN_ANGLE);

        moleculeGroup.getChildren().add(moleculeXform);

        world.getChildren().addAll(moleculeGroup);
    }
    
    public void setHeightWidth() {
    	
    	subScene.widthProperty().bind(((AnchorPane)group.getParent()).widthProperty());
    	subScene.heightProperty().bind(((AnchorPane)group.getParent()).heightProperty());
    	
    }
    
    public Group addMoly() {
        
    	System.out.println("starting up 3d rendering window");
        root.getChildren().add(vehicle);
        root.setDepthTest(DepthTest.ENABLE);
        buildCamera();
        buildAxes();
        frontLinks();

        subScene = new SubScene(root, 400, 400, true, SceneAntialiasing.BALANCED);
        subScene.setFill(Color.GREY);
                
        handleKeyboard(subScene, vehicle);
        handleMouse(subScene, vehicle);

        subScene.setCamera(camera);
        group.getChildren().add(subScene);
        return group;
    }
    
    public void addShape() {
    	
    	final PhongMaterial whiteMaterial = new PhongMaterial();
        whiteMaterial.setDiffuseColor(Color.LIGHTSKYBLUE);
        whiteMaterial.setSpecularColor(Color.LIGHTBLUE);
    	
        int xNumber = ThreadLocalRandom.current().nextInt(1, 100 + 1);
        int yNumber = ThreadLocalRandom.current().nextInt(1, 100 + 1);
        int rotateNumber = ThreadLocalRandom.current().nextInt(1, 360 + 1);
        
    	Box newBox = new Box(20,20,20);
    	MeshView mesh = new MeshView();
    	
    	
    	newBox.setMaterial(whiteMaterial);
        newBox.setTranslateX(xNumber);
        newBox.setTranslateY(yNumber);
        newBox.setRotate(rotateNumber);
        
        vehicle.getChildren().add(newBox);
    	
    	
    }
}
