package se.nackademin.objp.in3;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Pair;

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
            int tileId = ((Tile) actionEvent.getSource()).ID;
            if (model.areAdjacent(0, tileId)) {
                model.swap(0, tileId);
                view.getBoardView().placeTile(
                        (Tile) actionEvent.getSource(),
                        model.getRow(tileId),
                        model.getColumn(tileId));
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
            view.disableBoard();
        }
    }

    private BoardView createBoardView() {
        BoardView boardView = new BoardView();
        for (Integer i : model.boardState) {
            if (i == PuzzleModel.EMPTYSPACE) continue;
            Tile tb = new Tile(i, tileHandler);
            boardView.placeTile(tb, model.getRow(i), model.getColumn(i));
        }
        return boardView;
    }

    PuzzleView getView() {
        return view;
    }

    void newGame(int rows, int cols) {
        model = new PuzzleModel(rows, cols);
        model.shuffle();
        view.setBoardView(createBoardView());
        view.setMessage(rows + " rader, " + cols + " kolumner");
    }
}
