package javafx.prod;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    public static Stage mainStage;
    public static final int width = 1200, height = 800;
    public String css = Objects.requireNonNull(this.getClass().getResource("/javafx/prod/styles/style.css")).toExternalForm();

    public static Stage getMainStage() {
        return mainStage;
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("startScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        scene.getStylesheets().add(css);
        stage.setTitle("Production Application");
        stage.setScene(scene);

        stage.show();
    }
}
