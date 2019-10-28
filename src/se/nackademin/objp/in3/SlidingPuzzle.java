package se.nackademin.objp.in3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SlidingPuzzle extends Application {
    @Override
    public void start(Stage primaryStage) {
        SlidingPuzzleController controller = new SlidingPuzzleController();
        SlidingPuzzleView root = controller.getView();

        primaryStage.setScene(new Scene(root));
        primaryStage.getScene().getStylesheets().add("style.css");
        primaryStage.setTitle("15");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
