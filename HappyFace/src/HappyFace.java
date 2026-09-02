import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;
import javafx.scene.control.Button; 
import javafx.scene.control.Label;     
import javafx.scene.control.TextField; 
import javafx.scene.paint.Color;

public class HappyFace extends Application
{
    private boolean isHappy = true;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Group root = new Group();
        Scene scene = new Scene(root, 400, 450); 
        Canvas canvas = new Canvas(400, 260);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        drawFace(gc, 70, 35, 180);


        Label widthLabel = new Label("Mouth Width:");
        widthLabel.setLayoutX(10);
        widthLabel.setLayoutY(280);
        TextField widthInput = new TextField("70"); 
        widthInput.setLayoutX(90);
        widthInput.setLayoutY(275);
        widthInput.setPrefWidth(50);

        Label heightLabel = new Label("Mouth Height:");
        heightLabel.setLayoutX(150);
        heightLabel.setLayoutY(280);
        TextField heightInput = new TextField("35");
        heightInput.setLayoutX(235);
        heightInput.setLayoutY(275);
        heightInput.setPrefWidth(45);

        Label degreeLabel = new Label("Degrees:");
        degreeLabel.setLayoutX(290);
        degreeLabel.setLayoutY(280);
        TextField degreeInput = new TextField("180");
        degreeInput.setLayoutX(345);
        degreeInput.setLayoutY(275);
        degreeInput.setPrefWidth(45);


        Button updateButton = new Button("Update Dimensions");
        updateButton.setLayoutX(40);
        updateButton.setLayoutY(330);

        Button expressionButton = new Button("Change to Sad");
        expressionButton.setLayoutX(220);
        expressionButton.setLayoutY(330);


        updateButton.setOnAction(event -> {
            try {
                int w = Integer.parseInt(widthInput.getText());
                int h = Integer.parseInt(heightInput.getText());
                int d = Integer.parseInt(degreeInput.getText());

                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                drawFace(gc, w, h, d);
            } catch (NumberFormatException e) {
                System.out.println("Please enter valid whole numbers only inside fields.");
            }
        });

        expressionButton.setOnAction(event -> {
            isHappy = !isHappy;
            
            if (isHappy) {
                expressionButton.setText("Change to Sad");
            } else {
                expressionButton.setText("Change to Happy");
            }

            updateButton.fire(); 
        });

        root.getChildren().addAll(canvas, widthLabel, widthInput, heightLabel, heightInput,
                                  degreeLabel, degreeInput, updateButton, expressionButton);
        
        primaryStage.setTitle("Happy Face! LOLOL");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawFace(GraphicsContext gc, int mouthWidth, int mouthHeight, int degree) {
        int headSize = 150;
        int headX = 200 - (headSize / 2);
        int headY = 140 - (headSize / 2);

        gc.setFill(Color.LEMONCHIFFON);
        gc.fillOval(headX, headY, headSize, headSize);
        

        gc.setStroke(Color.DARKSLATEGRAY);
        gc.setLineWidth(3);
        gc.strokeOval(headX, headY, headSize, headSize);


        int eyeY = headY + (headSize * 2 / 5);
        int leftEyeX = headX + (headSize * 11 / 40);
        int rightEyeX = headX + (headSize * 26 / 40);
        int eyeW = headSize / 20;
        int eyeH = headSize / 10;

        gc.setFill(Color.DARKSLATEGRAY);
        gc.fillOval(leftEyeX, eyeY, eyeW, eyeH);
        gc.fillOval(rightEyeX, eyeY, eyeW, eyeH);

        int faceCenterX = 200;
        int faceMouthBaselineY = headY + 105; 
        
        int mouthX = faceCenterX - (mouthWidth / 2);
        
        if (isHappy) {

            int mouthY = faceMouthBaselineY - (mouthHeight / 2);
            gc.setStroke(Color.CRIMSON);
            gc.strokeArc(mouthX, mouthY, mouthWidth, mouthHeight, 180, degree, ArcType.OPEN);
        } else {

            int mouthY = faceMouthBaselineY - (mouthHeight / 2);
            gc.setStroke(Color.ROYALBLUE);
            gc.strokeArc(mouthX, mouthY, mouthWidth, mouthHeight, 0, degree, ArcType.OPEN);
        }
    }
}
