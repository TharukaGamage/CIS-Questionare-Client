package controllers;

import helpers.WindowSwitcher;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import managers.QuestionareDataManager;
import models.Region;
import models.RegionList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.GeoLocationClient;

import java.io.IOException;
import java.net.URL;
import java.util.*;

//Created by Tharuka Gamage on 20/11/20.
//Copyright © 2020 Tharuka Gamage. All rights reserved.

public class ControllerAddLiving implements Initializable {

    List<Region> regions;
    @FXML
    ChoiceBox<String> choiceLive;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        getRegions();
        choiceLive.getItems().addAll("Colombo", "Kandy", "Galle", "Matara", "Jaffna", "Hambantota", "Kaluthara", "Dambulla", "Monaragala");
        choiceLive.setValue("Colombo");

    }

//    getting regions list from GeoDB Cities API
    private void getRegions(){

        Map<String,String> headers = new HashMap<>();
        headers.put("x-rapidapi-key","0ba6f6cb3bmsh20b0b1635b45038p1a9fe8jsnb2f4be257b44");
        headers.put("x-rapidapi-host","wft-geo-db.p.rapidapi.com");

        GeoLocationClient client = new GeoLocationClient();
        Call<RegionList> call = client.getService().getRegionList(headers);

        call.enqueue(
                new Callback<RegionList>() {
                    @Override
                    public void onResponse(Call<RegionList> call, Response<RegionList> response) {
                        if(response.isSuccessful()){
                            System.out.println(response.body().getData().get(0).getName());
                            regions = response.body().getData();
                            for(Region i: regions){
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        choiceLive.getItems().add(i.getName());
                                    }
                                });
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<RegionList> call, Throwable t) {

                    }
                }
        );

    }

    @FXML
    private void btnNextLive() {
        System.out.println(choiceLive.getValue());
        QuestionareDataManager.getInstance().getCurrentQuestionare().setLiving(choiceLive.getValue());
        moveToNext();
    }

    private void moveToNext() {
        Stage window = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/controller_add_own_vehicle.fxml"));
        try {
            WindowSwitcher.getInstance().setWindow(window, loader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
