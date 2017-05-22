package sample;

import com.google.googlejavaformat.java.Formatter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        App.getmInstance().setFormatter(new Formatter());
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

//        FXMLLoader fxmlLoader = new FXMLLoader();
//        Pane p = fxmlLoader.load(getClass().getResource("sample.fxml").openStream());
//        controller = (Controller) fxmlLoader.getController();

        primaryStage.setTitle("Andro Repo");
        primaryStage.setScene(new Scene(root, 1000, 800));
//        primaryStage.setFullScreen(true);
        primaryStage.setResizable(true);
        primaryStage.setMinHeight(800);
        primaryStage.setMinWidth(1000);
        primaryStage.show();

        controller = App.getmInstance().getController();

//        for(int i=0;i<5;i++){
//            controller.addToView("titlle "+i);
//        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
