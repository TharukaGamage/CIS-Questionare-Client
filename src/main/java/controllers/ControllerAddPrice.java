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

public class ControllerAddPrice implements Initializable {
    @FXML
    ChoiceBox<String> choicePrice;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            if(QuestionareDataManager.getInstance().getCurrentQuestionare().equals("Motor Cycle")){
                choicePrice.getItems().addAll("100000","200000","300000","400000","500000","600000","700000","600000");
                choicePrice.setValue("100000");
            }else {
                choicePrice.getItems().addAll("1000000","1500000","2000000","2500000","3000000","3500000","4000000","4500000","5000000");
                choicePrice.setValue("2000000");
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @FXML
    private void btnNextPrice() {
        System.out.println(choicePrice.getValue());
        QuestionareDataManager.getInstance().getCurrentQuestionare().setPrice(Float.parseFloat(choicePrice.getValue()));
        moveToNext();
    }

    private void moveToNext() {
        Stage window = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/controller_add_happy.fxml"));
        try {
            WindowSwitcher.getInstance().setWindow(window, loader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
