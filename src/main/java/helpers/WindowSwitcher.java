package helpers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

//this singleton helps to switch between windows
public class WindowSwitcher {

    private Stage window = null;

    private static WindowSwitcher instance = new WindowSwitcher();

    private WindowSwitcher() {
    }

//    inject this instance
    public static WindowSwitcher getInstance() {
        return instance;
    }

    public void setWindow(
            Stage window,
            FXMLLoader loader) throws IOException {

//        init window if not instanciated
        if(this.window==null){
            this.window=window;
        }

        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        this.window.setScene(scene);
        this.window.show();
    }


}
