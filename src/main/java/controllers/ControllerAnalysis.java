package controllers;

import helpers.AuthHelper;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import models.Questionare;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.QuestionareClient;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

//Created by Tharuka Gamage on 20/11/20.
//Copyright © 2020 Tharuka Gamage. All rights reserved.

public class ControllerAnalysis implements Initializable {

    private List<Questionare> qList;
    private String twenty = "18-25";
    private String thirty = "25-35";
    private String forty = "35-45";
    private String fifty = "45-55";
    private String sisty = "55-65";

    private int gone = 0;
    private int gtwo = 0;
    private int gthree = 0;
    private int gfour = 0;
    private int gfive = 0;


    @FXML
    Label lbltotal;
    @FXML
    Label lblHappy;
    @FXML
    Label lblOwn;
    @FXML
    Label lblACars;
    @FXML
    Label lblAbikes;
    @FXML
    Label lblTwenty;
    @FXML
    Label lblthirty;
    @FXML
    Label lblForty;
    @FXML
    Label lblFifty;
    @FXML
    Label lblSixty;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getQList();
    }

    private void getQList() {
        QuestionareClient client = new QuestionareClient();
        Call<List<Questionare>> call = client.getService().getAnswers(AuthHelper.token);
        call.enqueue(new Callback<List<Questionare>>() {
            @Override
            public void onResponse(Call<List<Questionare>> call, Response<List<Questionare>> response) {
                if (response.isSuccessful()) {
                    qList = response.body();
                    initAnalysis();
                } else {
                    showError("Oops", "Something went wrong", "error in getting data");
                }
            }

            @Override
            public void onFailure(Call<List<Questionare>> call, Throwable t) {
                showError("Oops", "Something went wrong", "unknown error");
            }
        });
    }

    private void initAnalysis() {
        try {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    lbltotal.setText(Integer.toString(qList.size()));
                    happy();
                    own();
                    average();
                    showMostAgeGroup();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showMostAgeGroup() {
        int successor =  gone;
        for (int i = 0; i < qList.size(); i++) {
            if (qList.get(i).getAgeGroup().equals(twenty)) {
                gone++;
                lblTwenty.setText(Integer.toString(gone));
            } else if (qList.get(i).getAgeGroup().equals(thirty)) {
                gtwo++;
                lblthirty.setText(Integer.toString(gtwo));
            }else if (qList.get(i).getAgeGroup().equals(forty)) {
                gthree++;
                lblForty.setText(Integer.toString(gthree));
            }else if (qList.get(i).getAgeGroup().equals(fifty)) {
                gfour++;
                lblFifty.setText(Integer.toString(gfour));
            }else if (qList.get(i).getAgeGroup().equals(sisty)) {
                gfive++;
                lblSixty.setText(Integer.toString(gfive));
            }
        }
    }

    private void happy(){
        System.out.println("here");
        int happy = 0;
        for (int i = 0; i < qList.size(); i++) {
            System.out.println(qList.get(i).isHappy_with_price());
            if(qList.get(i).isWilling_to_buy()){
                happy++;
                System.out.println(happy);
            }
        }
        float x = (happy/(float)qList.size())*100;
        lblHappy.setText(x +"%");
    }

    private void own(){
        System.out.println("here");
        int happy = 0;
        for (int i = 0; i < qList.size(); i++) {
            System.out.println(qList.get(i).isOwn_a_vehivle());
            if(qList.get(i).isOwn_a_vehivle()){
                happy++;
                System.out.println(happy);
            }
        }
        float x = (happy/(float)qList.size())*100;
        lblOwn.setText(x +"%");
    }

    private void average(){
        System.out.println("here");
        int bikeC = 0;
        int cardC = 0;
        float bike = 0;
        float car = 0;
        for (int i = 0; i < qList.size(); i++) {
            System.out.println(qList.get(i).isOwn_a_vehivle());
            if(qList.get(i).getType().equals("Motor Cycle")){
                bike = bike+qList.get(i).getPrice();
                bikeC++;
                lblAbikes.setText(Float.toString(bike/bikeC)+"LKR");
                System.out.println(bike);
            }else {
                car= car+qList.get(i).getPrice();
                cardC++;
                lblACars.setText(Float.toString(car/cardC)+"LKR");
            }        }
    }


    private void showError(String message, String header, String title) {
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
