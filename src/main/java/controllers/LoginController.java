package controllers;

import helpers.AuthHelper;
import helpers.WindowSwitcher;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.LoginRequest;
import models.LoginResponse;
import models.SignUpRequest;
import models.SignUpResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.QuestionareClient;
import services.QuestionareService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//Created by Tharuka Gamage on 20/11/20.
//Copyright © 2020 Tharuka Gamage. All rights reserved.

public class LoginController implements Initializable {

    @FXML
    TextArea txtUserName;
    @FXML
    TextArea txtPassword;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void btnLoginClick() throws IOException {
        try {
            logIn();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnOnClickSignUp() {
        if(txtUserName.getText().isEmpty()||txtPassword.getText().isEmpty()){
            showError("password and username cannot be empty");
        }else {
            signUp();
        }

    }

    private void signUp() {
        QuestionareClient cllient = new QuestionareClient();
        Call<SignUpResponse> call = cllient.getService().SignUp(new SignUpRequest(txtUserName.getText(), txtPassword.getText()));
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                if (response.isSuccessful()) {
                    SignUpResponse signUpResponse = response.body();
                    if (signUpResponse.isSuccess()) {
                        AuthHelper.token = signUpResponse.getToken();
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                moveToNext();
                            }
                        });

                    }else {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                showError(signUpResponse.getMessage());
                            }
                        });
                    }                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {

            }
        });
    }

//    create login request and get response
    private void logIn() {
        QuestionareClient cllient = new QuestionareClient();
        Call<LoginResponse> call = cllient.getService().login(new LoginRequest(txtUserName.getText(), txtPassword.getText()));
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    if (loginResponse.isSuccess()) {
                        AuthHelper.token = loginResponse.getToken();
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                moveToNext();
                            }
                        });
                    } else {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                showError(loginResponse.getMessage());
                            }
                        });
                    }
                } else {
                    System.out.println(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                System.out.println(t.getStackTrace());
            }
        });
    }

//    show error alert
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ooops");
        alert.setHeaderText("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

//    switch to next step
    private void moveToNext() {
        Stage window = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/controller_get_started.fxml"));
        try {
            WindowSwitcher.getInstance().setWindow(window, loader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
