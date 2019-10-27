package se.nackademin.objp.in3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class SlidePuzzle extends Application {
    private BorderPane root = new BorderPane();
    private BoardView boardView = new BoardView();
    private FlowPane buttonPane = new FlowPane();

    public BoardView getBoardView() {
        return boardView;
    }

    public void setBoardView(BoardView boardView) {
        this.boardView = boardView;
        root.setCenter(boardView);
    }

    @Override
    public void start(Stage primaryStage) {
        SlidingPuzzleController controller = new SlidingPuzzleController(this);
        Button newGameButton = new Button("Nytt spel");
        newGameButton.setOnAction(controller.newGameHandler);
        Button settingsButton = new Button("Inställningar");
        Button exitButton = new Button("Avsluta");
        exitButton.setOnAction(controller.exitHandler);
        buttonPane.getChildren().addAll(newGameButton, settingsButton, exitButton);

        root.setCenter(boardView);
        root.setBottom(buttonPane);

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("15");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
