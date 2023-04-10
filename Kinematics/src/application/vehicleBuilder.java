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
	
	private linkSetup[][] suspensionLinks = new linkSetup[2][5];
	Group group = new Group();
	SubScene subScene;
	final Group root = new Group();
    final groupForm axisGroup = new groupForm();
    final groupForm world = new groupForm();
    final groupForm vehicle = new groupForm();
    final groupForm suspensionLinksGroupForm = new groupForm();
    final cogSetup cogGroupForm = new cogSetup();
    final PerspectiveCamera camera = new PerspectiveCamera(true);
    final groupForm cameraGroupForm = new groupForm();
    final groupForm cameraGroupForm2 = new groupForm();
    final groupForm cameraGroupForm3 = new groupForm();
    private static final double CAMERA_INITIAL_DISTANCE = -600;
    private static final double CAMERA_INITIAL_X_ANGLE = 70.0;
    private static final double CAMERA_INITIAL_Y_ANGLE = 320.0;
    private static final double CAMERA_NEAR_CLIP = 0.1;
    private static final double CAMERA_FAR_CLIP = 10000.0;
    private static final double AXIS_LENGTH = 250.0;
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
    
    //Needs looked at
    private void buildCamera() {
        System.out.println("buildCamera()");
        root.getChildren().add(cameraGroupForm);
        cameraGroupForm.getChildren().add(cameraGroupForm2);
        cameraGroupForm2.getChildren().add(cameraGroupForm3);
        cameraGroupForm3.getChildren().add(camera);
        cameraGroupForm3.setRotateZ(180.0);

        camera.setNearClip(CAMERA_NEAR_CLIP);
        camera.setFarClip(CAMERA_FAR_CLIP);
        camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
        cameraGroupForm.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
        cameraGroupForm.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
    }
    
    //These are the axis for reference
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
        suspensionLinksGroupForm.getChildren().addAll(axisGroup);
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
                    cameraGroupForm.ry.setAngle(cameraGroupForm.ry.getAngle() - mouseDeltaX*MOUSE_SPEED*modifier*ROTATION_SPEED);
                    cameraGroupForm.rx.setAngle(cameraGroupForm.rx.getAngle() + mouseDeltaY*MOUSE_SPEED*modifier*ROTATION_SPEED);
                }
                else if (me.isSecondaryButtonDown()) {
                    double z = camera.getTranslateZ();
                    double newZ = z + mouseDeltaX*MOUSE_SPEED*modifier;
                    camera.setTranslateZ(newZ);
                }
                else if (me.isMiddleButtonDown()) {
                    cameraGroupForm2.t.setX(cameraGroupForm2.t.getX() + mouseDeltaX*MOUSE_SPEED*modifier*TRACK_SPEED);
                    cameraGroupForm2.t.setY(cameraGroupForm2.t.getY() + mouseDeltaY*MOUSE_SPEED*modifier*TRACK_SPEED);
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
                        cameraGroupForm2.t.setX(0.0);
                        cameraGroupForm2.t.setY(0.0);
                        camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
                        cameraGroupForm.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
                        cameraGroupForm.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
                        break;
                    case X:
                        axisGroup.setVisible(!axisGroup.isVisible());
                        break;
                    case V:
                        vehicle.setVisible(!vehicle.isVisible());
                        break;
                }
            }
        });
    }
    
    private void wheels() {
    	
    	
    	
    }
    
    private void cog() {
    	
    	System.out.println("Cog being set up");
    	cogGroupForm.getChildren().addAll(cogGroupForm.cog);
    	
    }
    
    public void cogAdjustment(int xyz, double arg2) {
    	
    	cogGroupForm.adjustments(xyz,arg2);
    	
    }
    
    private void suspension() {
    	
    	System.out.println("Building Suspension");
    	
    	for(int frontRear = 0;frontRear<2;frontRear++) {
    		for(int linkNumber = 0;linkNumber<5;linkNumber++) {
    			
    			suspensionLinks[frontRear][linkNumber] = new linkSetup();
    			suspensionLinksGroupForm.getChildren().addAll(suspensionLinks[frontRear][linkNumber].link);
    			
    		}
    	}
    }
   
    public void suspensionAdjustment(int frontRear,int linkNumber,int inboardOutboard,int xYZ,double arg2) {
    	
    	suspensionLinks[frontRear][linkNumber].adjustments(inboardOutboard,xYZ,arg2);
    	
    }
    
    public void setHeightWidth() {
    	
    	subScene.widthProperty().bind(((AnchorPane)group.getParent()).widthProperty());
    	subScene.heightProperty().bind(((AnchorPane)group.getParent()).heightProperty());
    	
    }
    
    public Group createVehicle() {
    	
    	System.out.println("starting up 3d rendering window");
        root.getChildren().add(suspensionLinksGroupForm);
        root.getChildren().add(cogGroupForm);
        root.setDepthTest(DepthTest.ENABLE);
        buildCamera();
        buildAxes();
        suspension();
        cog();

        subScene = new SubScene(root, 400, 400, true, SceneAntialiasing.BALANCED);
        subScene.setFill(Color.GREY);
                
        handleKeyboard(subScene, suspensionLinksGroupForm);
        handleMouse(subScene, suspensionLinksGroupForm);

        subScene.setCamera(camera);
        group.getChildren().add(subScene);
    	
    	return group;
    }
    
}
