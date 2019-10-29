package se.nackademin.objp.in3;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PuzzleController {
    private PuzzleView view;
    private PuzzleModel model;
    private int rows = 4;
    private int cols = 4;

    EventHandler<ActionEvent> newGameHandler = actionEvent -> newGame(rows, cols);
    EventHandler<ActionEvent> exitHandler = actionEvent -> System.exit(0);

    EventHandler<ActionEvent> settingsHandler = (actionEvent -> {
        Optional<Pair<Integer, Integer>> newSettings = view.changeSettings(rows, cols);
        newSettings.ifPresent(rowsAndCols -> {
            this.rows = rowsAndCols.getKey();
            this.cols = rowsAndCols.getValue();
            newGame(rows, cols);
        });
    });

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

    PuzzleController() {
        view = new PuzzleView(this);
        newGame(rows, cols);
    }

    private void checkWinning() {
        if (model.isSolved()) {
            view.setMessage("Grattis! Du har vunnit!");
            view.setBoardDisabled(true);
        }
    }

    PuzzleView getView() {
        return view;
    }

    void newGame(int rows, int cols) {
        model = new PuzzleModel(rows, cols);
        model.shuffle();
        resetBoardView();
        view.setMessage(rows + " rader, " + cols + " kolumner");
    }

    private void resetBoardView() {
        List<Tile> newBoard = new ArrayList<>();
        for (Integer i : model.boardState) {
            if (i == PuzzleModel.EMPTYSPACE) continue;
            Tile tb = new Tile(i, tileHandler, model.getRow(i), model.getColumn(i));
            newBoard.add(tb);
        }
        view.resetBoard(newBoard);
    }
}
