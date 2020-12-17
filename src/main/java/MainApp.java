
import controllers.LoginController;
import helpers.WindowSwitcher;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import java.io.IOException;


public class MainApp extends Application {

    Stage window;


    @Override
    public void start(Stage primaryStage) throws IOException {
        window = primaryStage;
        primaryStage.setTitle("Questionnaire on vehicle prices");
        setWindow();
    }
    private void setWindow() throws IOException{
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("login_controller.fxml"));
            WindowSwitcher.getInstance().setWindow(
                    window,
                    loader);

            LoginController loginController = loader.getController();

        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("at the end");
    }

//    starting point of the application
    public static void main(String[] args) {
        System.out.println("here");
        launch(args);
    }
}
