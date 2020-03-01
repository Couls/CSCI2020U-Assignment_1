package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.*;

public class ThreeCards extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Pane pane = new HBox(5);
        pane.setPadding(new Insets(5,5,5,5));
        //pick three random numbers from 1 to 54
        int first = (int)(Math.random()*((54-1)+1))+1; // 1-54
        int second = (int)(Math.random()*((54-1)+1))+1;
        int third = (int)(Math.random()*((54-1)+1))+1;
        //create three random cards from the numbers chosen
        Image card1 = new Image(randomdir(first));
        Image card2 = new Image(randomdir(second));
        Image card3 = new Image(randomdir(third));
        //display the three random cards
        ImageView imageView1 = new ImageView(card1);
        pane.getChildren().add(imageView1);

        ImageView imageView2 = new ImageView(card2);
        pane.getChildren().add(imageView2);

        ImageView imageView3 = new ImageView(card3);
        pane.getChildren().add(imageView3);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ShowImage");
        primaryStage.show();
    }
    // returns the string directory from the numbers provided
    public String randomdir(int dir) {
        return "Cards/" + dir + ".png";
    }

    public static void main(String[] args) {
        launch(args);
    }
}
