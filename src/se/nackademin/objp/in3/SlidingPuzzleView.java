package se.nackademin.objp.in3;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

public class SlidingPuzzleView extends BorderPane {
    private BoardView boardView = new BoardView();

    SlidingPuzzleView(SlidingPuzzleController controller) {
        super();
        Button newGameButton = new Button("Nytt spel");
        newGameButton.setOnAction(controller.newGameHandler);
        Button settingsButton = new Button("Inställningar");
        Button exitButton = new Button("Avsluta");
        exitButton.setOnAction(controller.exitHandler);

        FlowPane buttonPane = new FlowPane();
        buttonPane.getChildren().addAll(newGameButton, settingsButton, exitButton);

        setCenter(boardView);
        setBottom(buttonPane);
    }

    void disableBoard() {
        boardView.setDisable(true);
    }

    protected BoardView getBoardView() {
        return boardView;
    }

    protected void setBoardView(BoardView boardView) {
        this.boardView = boardView;
        this.setCenter(boardView);
    }

    void reportWin() {
        Alert winMsg = new Alert(Alert.AlertType.INFORMATION);
        winMsg.setTitle(null);
        winMsg.setHeaderText("Grattis!");
        winMsg.setContentText("Du vann!");
        // Gör något med val?
        winMsg.showAndWait();
    }
}
