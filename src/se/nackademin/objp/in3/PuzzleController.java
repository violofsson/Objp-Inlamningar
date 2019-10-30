package se.nackademin.objp.in3;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PuzzleController {
    private PuzzleModel model;
    private int rows = 4;
    private int cols = 4;

    @FXML
    GridPane board;

    @FXML
    Text message;

    @FXML
    EventHandler<ActionEvent> newGameHandler = actionEvent -> newGame();
    @FXML
    EventHandler<ActionEvent> exitHandler = actionEvent -> System.exit(0);

    @FXML
    EventHandler<ActionEvent> settingsHandler = (actionEvent -> {
        Optional<Pair<Integer, Integer>> newSettings = Optional.empty();
        try {
            newSettings = PuzzleView.changeSettings(rows, cols);
        } catch (Exception e) {
            e.printStackTrace();
        }
        newSettings.ifPresent(rowsAndCols -> {
            this.rows = rowsAndCols.getKey();
            this.cols = rowsAndCols.getValue();
            newGame();
        });
    });

    @FXML
    void changeSettings() {
        Optional<Pair<Integer, Integer>> newSettings = Optional.empty();
        try {
            SettingsDialog dialog = new SettingsDialog(rows, cols);
            newSettings = dialog.showAndWait();;
        } catch (Exception e) {
            e.printStackTrace();
        }
        newSettings.ifPresent(rowsAndCols -> {
            this.rows = rowsAndCols.getKey();
            this.cols = rowsAndCols.getValue();
            newGame();
        });
    }

    EventHandler<ActionEvent> tileHandler = (actionEvent -> {
        if (actionEvent.getSource() instanceof Tile) {
            Tile t = (Tile) actionEvent.getSource();
            if (model.areAdjacent(0, t.ID)) {
                model.swap(0, t.ID);
                t.move(model.getRow(t.ID), model.getColumn(t.ID));
            }
            checkWinning();
        }
    });

    /*PuzzleController() {
        //view = new PuzzleView(this);
        newGame();
    }*/

    @FXML
    public void initialize() {
        newGame();
    }

    private void checkWinning() {
        if (model.isSolved()) {
            message.setText("Grattis! Du har vunnit!");
            board.setDisable(true);
        }
    }

    @FXML
    void newGame() {
        model = new PuzzleModel(rows, cols);
        model.shuffle();
        resetBoardView();
        message.setText(rows + " rader, " + cols + " kolumner");
    }

    private void resetBoardView() {
        board.getChildren().clear();
        List<Tile> newBoard = new ArrayList<>();
        for (Integer i : model.boardState) {
            if (i == PuzzleModel.EMPTYSPACE) continue;
            Tile tb = new Tile(i, tileHandler, model.getRow(i), model.getColumn(i));
            newBoard.add(tb);
        }
        board.getChildren().addAll(newBoard);
        board.setDisable(false);
    }

    @FXML
    void quit() {
        System.exit(0);
    }
}
