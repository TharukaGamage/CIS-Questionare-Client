package controllers;

import helpers.AuthHelper;
import helpers.WindowSwitcher;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import managers.QuestionareDataManager;


//Created by Tharuka Gamage on 20/11/20.
//Copyright © 2020 Tharuka Gamage. All rights reserved.

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerAddWait implements Initializable {
    @FXML
    RadioButton radYes;
    @FXML
    RadioButton radNo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void btnNextWait() {
        if (radYes.isSelected()) {
            setChoice(true);
        } else if (radNo.isSelected()) {
            setChoice(false);
        } else {
            showError("please select your choice","cannot proceed","Oops");
        }
    }

    private void moveToNext() {
        Stage window = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/controller_thank_you.fxml"));
        try {
            WindowSwitcher.getInstance().setWindow(window, loader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void setChoice(boolean choice) {
        System.out.println(choice);
        QuestionareDataManager.getInstance().getCurrentQuestionare().setWait_for_drop(choice);
        moveToNext();
    }
    private void showError(String message,String header,String title) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(title);
                alert.setHeaderText(header);
                alert.setContentText(message);
                alert.showAndWait();
            }
        });
    }
}
