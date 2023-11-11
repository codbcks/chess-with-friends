package nz.chess;

import java.io.IOException;

import javafx.application.Application;  
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;  
import javafx.stage.Stage;  
public class ChessApp extends Application {
    private static Scene scene;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            setRoot("chess");
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage.setTitle("Chess");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene = new Scene(loadFxml(fxml), 800, 800);
    }

    private static Parent loadFxml(final String fxml) throws IOException {
        return new FXMLLoader(ChessApp.class.getResource("/fxml/" + fxml + ".fxml")).load();
    }
}