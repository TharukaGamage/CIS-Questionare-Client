package controllers;

import helpers.WindowSwitcher;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import managers.QuestionareDataManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//Created by Tharuka Gamage on 20/11/20.
//Copyright © 2020 Tharuka Gamage. All rights reserved.

public class ControllerAddVehicleType implements Initializable {
    @FXML
    ChoiceBox<String> choiceCatagory;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        choiceCatagory.getItems().addAll("SUV","Van","crew Cab","Car","Motor Cycle");
        choiceCatagory.setValue("SUV");

    }

    @FXML
    private void btnNextType() {
        System.out.println(choiceCatagory.getValue());
        QuestionareDataManager.getInstance().getCurrentQuestionare().setType(choiceCatagory.getValue());
        moveToNext();
    }

    private void moveToNext() {
        Stage window = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/controller_add_vehicle_price.fxml"));
        try {
            WindowSwitcher.getInstance().setWindow(window, loader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
