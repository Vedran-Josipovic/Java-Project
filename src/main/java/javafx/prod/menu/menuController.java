package javafx.prod.menu;

import javafx.fxml.FXMLLoader;
import javafx.prod.HelloApplication;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

public class menuController {
    public String css = Objects.requireNonNull(this.getClass().getResource("/javafx/prod/styles/style.css")).toExternalForm();

    public void showAddressSearchScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(javafx.prod.HelloApplication.class.getResource("addressSearch.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), HelloApplication.width, HelloApplication.height);
            scene.getStylesheets().add(css);
            HelloApplication.getMainStage().setTitle("Address Search");
            HelloApplication.getMainStage().setScene(scene);
            HelloApplication.getMainStage().show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showAddressAddScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(javafx.prod.HelloApplication.class.getResource("addressAdd.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), HelloApplication.width, HelloApplication.height);
            scene.getStylesheets().add(css);
            HelloApplication.getMainStage().setTitle("Add an address");
            HelloApplication.getMainStage().setScene(scene);
            HelloApplication.getMainStage().show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showTransactionSearchScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(javafx.prod.HelloApplication.class.getResource("transactionSearch.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), HelloApplication.width, HelloApplication.height);
            scene.getStylesheets().add(css);
            HelloApplication.getMainStage().setTitle("Transaction Search");
            HelloApplication.getMainStage().setScene(scene);
            HelloApplication.getMainStage().show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showTransactionAddScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(javafx.prod.HelloApplication.class.getResource("transactionAdd.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), HelloApplication.width, HelloApplication.height);
            scene.getStylesheets().add(css);
            HelloApplication.getMainStage().setTitle("Add a transaction");
            HelloApplication.getMainStage().setScene(scene);
            HelloApplication.getMainStage().show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
