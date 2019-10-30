package se.nackademin.objp.in3;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.util.Optional;

public class PuzzleController {
    private PuzzleModel model;
    private int rows = 4;
    private int cols = 4;

    @FXML
    private GridPane board;

    @FXML
    private Text message;

    @FXML
    void changeSettings() {
        Optional<Pair<Integer, Integer>> newSettings = Optional.empty();
        try {
            SettingsDialog dialog = new SettingsDialog(rows, cols);
            newSettings = dialog.showAndWait();
            ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        newSettings.ifPresent(rowsAndCols -> {
            this.rows = rowsAndCols.getKey();
            this.cols = rowsAndCols.getValue();
            newGame();
        });
    }

    private EventHandler<ActionEvent> tileHandler = (actionEvent -> {
        if (actionEvent.getSource() instanceof Tile) {
            Tile t = (Tile) actionEvent.getSource();
            if (model.areAdjacent(0, t.ID)) {
                model.swap(0, t.ID);
                t.move(model.getRow(t.ID), model.getColumn(t.ID));
            }
            checkWinning();
        }
    });

    @FXML
    public void initialize() {
        newGame();
    }

    private void checkWinning() {
        if (model.isSolved()) {
            setMessage("Grattis! Du har vunnit!");
            setBoardDisable(true);
        }
    }

    @FXML
    void newGame() {
        model = new PuzzleModel(rows, cols);
        model.shuffle();
        resetBoardView();
        setMessage(rows + " rader, " + cols + " kolumner");
    }

    private void setBoardDisable(boolean b) {
        board.setDisable(b);
    }

    private void setMessage(String msg) {
        message.setText(msg);
    }

    private void resetBoardView() {
        board.getChildren().clear();
        for (Integer i : model.boardState) {
            if (i == PuzzleModel.EMPTYSPACE) continue;
            Tile tb = new Tile(i, tileHandler, model.getRow(i), model.getColumn(i));
            board.getChildren().add(tb);
        }
        setBoardDisable(false);
    }

    @FXML
    private void quit() {
        System.exit(0);
    }
}
