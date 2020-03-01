package sample;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PointsonaCircle extends Application {
    public void start(Stage primaryStage) {

        Pane pane = new TrianglePane();

        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setTitle("Points on a circle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static class TrianglePane extends Pane {
        private Circle maincircle;
        private Circle circle1, circle2, circle3;
        private Line line1, line2, line3;
        private Label label1, label2, label3;

        TrianglePane() {
            setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            setPadding(new Insets(30));
            //create the main circle that everything will rotate around
            maincircle = new Circle(200, 200, 100);
            maincircle.setFill(Color.WHITE);
            maincircle.setStroke(Color.BLACK);
            //create three red circles on the main circle
            circle1 = new Circle(10);
            circle1.setCenterX(maincircle.getCenterX() + maincircle.getRadius() * Math.cos(90));
            circle1.setCenterY(maincircle.getCenterY() - maincircle.getRadius() * Math.sin(90));
            circle1.setFill(Color.RED);
            circle1.setOnMouseDragged(e -> {
                double angle = Math.PI / 2 - Math.atan2(e.getX() - maincircle.getCenterX(), e.getY() - maincircle.getCenterY());
                circle1.setCenterX(maincircle.getCenterX() + maincircle.getRadius() * Math.cos(angle));
                circle1.setCenterY(maincircle.getCenterY() + maincircle.getRadius() * Math.sin(angle));
                displayAngles();
            });
            circle2 = new Circle(10);
            circle2.setCenterX(maincircle.getCenterX() + maincircle.getRadius() * Math.cos(180));
            circle2.setCenterY(maincircle.getCenterY() - maincircle.getRadius() * Math.sin(180));
            circle2.setFill(Color.RED);
            circle2.setOnMouseDragged(e -> {
                double angle = Math.PI / 2 - Math.atan2(e.getX() - maincircle.getCenterX(), e.getY() - maincircle.getCenterY());
                circle2.setCenterX(maincircle.getCenterX() + maincircle.getRadius() * Math.cos(angle));
                circle2.setCenterY(maincircle.getCenterY() + maincircle.getRadius() * Math.sin(angle));
                displayAngles();
            });
            circle3 = new Circle(10);
            circle3.setCenterX(maincircle.getCenterX() + maincircle.getRadius() * Math.cos(270));
            circle3.setCenterY(maincircle.getCenterY() - maincircle.getRadius() * Math.sin(270));
            circle3.setFill(Color.RED);
            circle3.setOnMouseDragged(e -> {
                double angle = Math.PI / 2 - Math.atan2(e.getX() - maincircle.getCenterX(), e.getY() - maincircle.getCenterY());
                circle3.setCenterX(maincircle.getCenterX() + maincircle.getRadius() * Math.cos(angle));
                circle3.setCenterY(maincircle.getCenterY() + maincircle.getRadius() * Math.sin(angle));
                displayAngles();
            });
            //draw three lines and bind them to the circles X and Y properties
            line1 = new Line();
            line1.startXProperty().bind(circle1.centerXProperty());
            line1.startYProperty().bind(circle1.centerYProperty());
            line1.endXProperty().bind(circle2.centerXProperty());
            line1.endYProperty().bind(circle2.centerYProperty());
            line2 = new Line();
            line2.startXProperty().bind(circle2.centerXProperty());
            line2.startYProperty().bind(circle2.centerYProperty());
            line2.endXProperty().bind(circle3.centerXProperty());
            line2.endYProperty().bind(circle3.centerYProperty());
            line3 = new Line();
            line3.startXProperty().bind(circle3.centerXProperty());
            line3.startYProperty().bind(circle3.centerYProperty());
            line3.endXProperty().bind(circle1.centerXProperty());
            line3.endYProperty().bind(circle1.centerYProperty());
            //create the labels for the angles and bind them to the circles
            label1 = new Label();
            label1.layoutXProperty().bind(circle1.centerXProperty().add(5));
            label1.layoutYProperty().bind(circle1.centerYProperty().add(5));
            label2 = new Label();
            label2.layoutXProperty().bind(circle2.centerXProperty().add(5));
            label2.layoutYProperty().bind(circle2.centerYProperty().add(5));
            label3 = new Label();
            label3.layoutXProperty().bind(circle3.centerXProperty().add(5));
            label3.layoutYProperty().bind(circle3.centerYProperty().add(5));
            //add everything to the pane
            getChildren().addAll(maincircle, line1, line2, line3, circle1, circle2, circle3, label1, label2, label3);
            //begin calculating the angles of each point
            displayAngles();
        }

        public void displayAngles() {
            double a = Math.sqrt(Math.pow(circle1.getCenterX() - circle2.getCenterX(), 2) +
                    Math.pow(circle1.getCenterY() - circle2.getCenterY(), 2));
            double b = Math.sqrt(Math.pow(circle2.getCenterX() - circle3.getCenterX(), 2) +
                    Math.pow(circle2.getCenterY() - circle3.getCenterY(), 2));
            double c = Math.sqrt(Math.pow(circle3.getCenterX() - circle1.getCenterX(), 2) +
                    Math.pow(circle3.getCenterY() - circle1.getCenterY(), 2));
            double A = Math.toDegrees(Math.acos((a * a - b * b - c * c) / (-2 * b * c)));
            double B = Math.toDegrees(Math.acos((b * b - a * a - c * c) / (-2 * a * c)));
            double C = Math.toDegrees(Math.acos((c * c - b * b - a * a) / (-2 * a * b)));
            label1.setText(String.format("%.2f", B));
            label2.setText(String.format("%.2f", C));
            label3.setText(String.format("%.2f", A));
        }
    }
}