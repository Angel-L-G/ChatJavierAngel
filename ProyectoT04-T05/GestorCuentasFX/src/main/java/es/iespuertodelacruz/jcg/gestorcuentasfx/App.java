package es.iespuertodelacruz.jcg.gestorcuentasfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage primaryScene;

    public static void setRoot(String fxml, int ancho, int alto) throws IOException {
        scene.setRoot(loadFXML(fxml));
        primaryScene.setWidth(ancho);
        primaryScene.setHeight(alto);
    }

    @Override
    public void start(Stage stage) throws IOException {
        primaryScene = stage;
        scene = new Scene(loadFXML("primary"), 1000, 500);
        stage.setScene(scene);
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}