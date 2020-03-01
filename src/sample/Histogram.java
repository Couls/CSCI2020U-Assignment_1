package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Histogram extends Application {

    public XYChart.Series series = new XYChart.Series();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Histogram Question");
        GridPane window = new GridPane();
        window.setAlignment(Pos.CENTER);
        window.setHgap(10);
        window.setVgap(10);
        window.setPadding(new Insets(5, 5, 5, 5));
        //barchart variables
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> bc =
                new BarChart<String, Number>(xAxis, yAxis);
        //labels and textfields
        Label filename = new Label("FilePath:");
        window.add(filename, 0, 1);
        TextField path = new TextField();
        window.add(path, 1, 1);
        HBox hbox = new HBox();
        Button View = new Button("View");
        hbox.getChildren().add(View);
        window.add(hbox, 0, 2);
        View.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //variables for alphabet and the amount of letters per alphabet
                char[] alpha = new char[26];
                int[] lettercount = new int[26];
                //use ASCII to fill the alpha array
                for (int i = 0; i < 26; i++) {
                    alpha[i] = (char) (65 + i);
                }
                try {
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(
                                    new FileInputStream(path.getText()), // find the file in the path provided
                                    Charset.forName("UTF-8")));
                    int c = 0;
                    while((c = reader.read()) != -1) {
                        char character = (char) c;
                        character = Character.toUpperCase(character); // read all the characters and convert to uppercase
                        for (int i = 0; i < 26; i++) {
                            if (character == alpha[i]) {
                                lettercount[i]++;
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 26; i++) {
                    String w = String.valueOf(alpha[i]);
                    series.getData().add(new XYChart.Data(w, lettercount[i])); // add every letter and the amount found to the graph
                }
            }
        });
        bc.setTitle("Histogram");
        xAxis.setLabel("Letter");
        yAxis.setLabel("Value");
        window.add(bc, 0, 0);
        Scene scene = new Scene(window, 800, 600);
        bc.getData().add(series); // add the data to the barchart
        stage.setScene(scene);
        stage.show();
    }
    
}
