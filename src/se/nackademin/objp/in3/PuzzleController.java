package se.nackademin.objp.in3;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
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

    /*EventHandler<ActionEvent> tileHandler = (actionEvent -> {
        if (actionEvent.getSource() instanceof Tile) {
            int tileId = ((Tile) actionEvent.getSource()).ID;
            if (model.areAdjacent(0, tileId)) {
                model.swap(0, tileId);
                view.getBoardView().moveTile(
                        (Tile) actionEvent.getSource(),
                        model.getRow(tileId),
                        model.getColumn(tileId));
            }
            checkWinning();
        }
    });*/

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
            view.disableBoard();
        }
    }

    private GridPane createBoardView() {
        GridPane boardView = new GridPane();
        boardView.getStyleClass().add("board-view");
        for (Integer i : model.boardState) {
            if (i == PuzzleModel.EMPTYSPACE) continue;
            Tile tb = new Tile(i, tileHandler, model.getRow(i), model.getColumn(i));
            boardView.getChildren().add(tb);
            //boardView.addTile(tb, model.getRow(i), model.getColumn(i));
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
