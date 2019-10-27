package se.nackademin.objp.in3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class SlidePuzzle extends Application {
    private BorderPane root = new BorderPane();
    private GridPane boardView = new GridPane();
    private FlowPane buttonPane = new FlowPane();

    public GridPane getBoardView() {
        return boardView;
    }

    public void setBoardView(GridPane boardView) {
        this.boardView = boardView;
        root.setCenter(boardView);
    }

    @Override
    public void start(Stage primaryStage) {
        Button newGameButton = new Button("Nytt spel");
        Button settingsButton = new Button("Inställningar");
        Button exitButton = new Button("Avsluta");
        buttonPane.getChildren().addAll(newGameButton, settingsButton, exitButton);

        root.setCenter(boardView);
        root.setBottom(buttonPane);

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
