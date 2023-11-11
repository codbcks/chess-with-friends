package nz.chess.code;

import javafx.application.Application;  
import javafx.event.ActionEvent;  
import javafx.event.EventHandler;  
import javafx.scene.Scene;  
import javafx.scene.control.Button;  
import javafx.stage.Stage;  
import javafx.scene.layout.StackPane;  
public class ChessApp extends Application{  
  
    @Override  
    public void start(Stage primaryStage) throws Exception {  
        // TODO Auto-generated method stub  
        Button btn1=new Button("Say, Hello World");  
        btn1.setOnAction(new EventHandler<ActionEvent>() {  
              
            @Override  
            public void handle(ActionEvent arg0) {  
                // TODO Auto-generated method stub  
                System.out.println("hello world");
                
            }  
        });  
        StackPane root=new StackPane();  
        root.getChildren().add(btn1);  
        Scene scene=new Scene(root,800,800);      
        primaryStage.setTitle("Chess");  
        primaryStage.setScene(scene);  
        primaryStage.show();  
    }  
    public static void main (String[] args)  
    {  
        launch(args);  
    }  
  
} 