package controllers;

import helpers.AuthHelper;
import helpers.WindowSwitcher;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import managers.QuestionareDataManager;
import models.BaseResponse;
import models.Questionare;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.QuestionareClient;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//Created by Tharuka Gamage on 20/11/20.
//Copyright © 2020 Tharuka Gamage. All rights reserved.

public class ControllerThankYou implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

//    submit button action

    @FXML
    private void btnSubmit(){
        Stage window = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/controller_analysis.fxml"));
        try {
            saveresponse();
            WindowSwitcher.getInstance().setWindow(window, loader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveresponse() {
        Questionare questionare = QuestionareDataManager.getInstance().getCurrentQuestionare();
        questionare.setToken(AuthHelper.token);
        QuestionareClient questionareService = new QuestionareClient();
        Call<BaseResponse> call = questionareService.getService().SaveAnswers(questionare);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful()) {
                    BaseResponse baseResponse = response.body();
                    if(baseResponse.isSuccess()){
                        showError("we have recorded your answers","Success","Great");
                    }else {
                        showError(baseResponse.getMessage(),"Couldn,t record response","Ooops");
                    }                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
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
