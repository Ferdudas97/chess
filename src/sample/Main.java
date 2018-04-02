package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader=new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("/sample/views/chess.fxml"));



        primaryStage.setScene(new Scene(root, Screen.getPrimary().getVisualBounds().getMaxX(),  Screen.getPrimary().getVisualBounds().getMaxY()-30));
        primaryStage.setTitle("Chess made by me :D");
        //primaryStage.setFullScreen(true);
        VBox vBox =new VBox();


        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
