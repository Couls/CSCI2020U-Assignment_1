package sample;

import javafx.application.Application;
import javafx.css.converter.StringConverter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.converter.*;

import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.*;

public class InvestmentValueCalculator extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane window = new GridPane();
        window.setAlignment(Pos.CENTER);
        window.setHgap(10);
        window.setVgap(10);
        window.setPadding(new Insets(5, 5, 5, 5));
        //Add textfields and labels to the calculator
        Label Investment = new Label("Investment Amount:");
        window.add(Investment, 0, 1);
        TextField investment = new TextField();
        window.add(investment, 1, 1);
        Label Years = new Label("Years:");
        window.add(Years, 0, 2);
        TextField years = new TextField();
        window.add(years, 1, 2);
        Label Interest = new Label("Annual Interest Rate:");
        window.add(Interest, 0, 3);
        TextField interest = new TextField();
        window.add(interest, 1, 3);
        //Add the future value label and an hbox that will contain it
        Label futurevalue = new Label("Future Value:");
        window.add(futurevalue, 0, 4);
        HBox futureV = new HBox(5);
        futureV.setMinWidth(10);
        window.add(futureV, 1, 4);
        Scene scene = new Scene(window, 300, 275);
        Button calculate = new Button("calculate");
        Label futures = new Label();
        futureV.getChildren().add(futures);
        calculate.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent e){
                //use a stringconverter to get doubles from the textfield
                DoubleStringConverter weird = new DoubleStringConverter();
                //store the ivestment amount, interest rate and years as variables
                double investmentAmount = weird.fromString(investment.getText());
                double annualInterestRate = weird.fromString(interest.getText()) / 100; // divide by 100 because its a percentage
                double year = weird.fromString(years.getText());
                double futureValue = investmentAmount * Math.pow(1 + annualInterestRate / 12, year * 12);
                //convert to string and set the label to display the answer
                futures.setText(weird.toString(futureValue));
            }
        });
        //create an hbox for the button
        HBox hbutton = new HBox(10);
        hbutton.setAlignment(Pos.CENTER_RIGHT);
        //add the button to the hbox's children
        hbutton.getChildren().add(calculate);
        window.add(hbutton, 1, 7);
        primaryStage.setTitle("Investment Value Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
