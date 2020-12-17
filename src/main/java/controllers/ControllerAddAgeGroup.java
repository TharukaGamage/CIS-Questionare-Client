package controllers;


import helpers.WindowSwitcher;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import managers.QuestionareDataManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//Created by Tharuka Gamage on 20/11/20.
//Copyright © 2020 Tharuka Gamage. All rights reserved.

public class ControllerAddAgeGroup implements Initializable {

    @FXML
    RadioButton age25;
    @FXML
    RadioButton age35;
    @FXML
    RadioButton age45;
    @FXML
    RadioButton age55;
    @FXML
    RadioButton age65;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    private void setAge(String ageGroup) {
        QuestionareDataManager.getInstance().getCurrentQuestionare().setAgeGroup(ageGroup);
        System.out.println(QuestionareDataManager.getInstance().getCurrentQuestionare().getAgeGroup());
    }

    @FXML
    private void btnNextAgeGroup() {
        try {

            if (age25.isSelected()) {
                setAge("18-25");
            }
            else    if (age35.isSelected()) {
                setAge("25-35");
            }
            else   if (age45.isSelected()) {
                setAge("35-45");
            }
            else  if (age55.isSelected()) {
                setAge("45-55");
            }
            else  if (age65.isSelected()) {
                setAge("55-65");
            } else {
                showMessage("please select item");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        Stage window = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/controller_add_living.fxml"));
        try {
            WindowSwitcher.getInstance().setWindow(window, loader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ooops");
        alert.setHeaderText("Cannot go forward");
        alert.setContentText(message);
        alert.showAndWait();
    }


}
