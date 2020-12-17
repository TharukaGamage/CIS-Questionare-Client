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

public class ControllerAddOwn implements Initializable {

    @FXML
    RadioButton radYes;
    @FXML
    RadioButton radNo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void btnNextOwn() {
        if (radYes.isSelected()) {
            setChoice(true);
        } else if (radNo.isSelected()) {
            setChoice(false);
        } else {
            showError("please select your choice");
        }
    }

    private void moveToNext() {
        Stage window = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/controller_add_willing_to_buy.fxml"));
        try {
            WindowSwitcher.getInstance().setWindow(window, loader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setChoice(boolean choice) {
        System.out.println(choice);
        QuestionareDataManager.getInstance().getCurrentQuestionare().setOwn_a_vehivle(choice);
        moveToNext();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ooops");
        alert.setHeaderText("Cannot proceed");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
