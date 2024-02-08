package javafx.prod.menu;

import javafx.fxml.FXMLLoader;
import javafx.prod.HelloApplication;
import javafx.scene.Scene;

import java.io.IOException;

public class menuController {
    public void showAddressSearchScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(javafx.prod.HelloApplication.class.getResource("addressSearch.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), HelloApplication.width, HelloApplication.height);
            HelloApplication.getMainStage().setTitle("Address Search");
            HelloApplication.getMainStage().setScene(scene);
            HelloApplication.getMainStage().show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
