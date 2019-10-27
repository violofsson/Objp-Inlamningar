package se.nackademin.objp.in3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class SlidingPuzzleController {
    SlidePuzzle view;
    SlidingPuzzleModel model;
    private int rows = 4;
    private int cols = 4;

    // Lyssnare för nytt spel
    EventHandler<ActionEvent> newGameHandler = actionEvent -> newGame(rows, cols);

    // Lyssnare för inställningar

    // Lyssnare för avslutning?
    EventHandler<ActionEvent> exitHandler = actionEvent -> System.exit(0);

    // Lyssnare för pusselbitar - de delar på en och identifieras
    // med motsvarande bitar i modellen
    EventHandler<ActionEvent> tileHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            if (actionEvent.getSource() instanceof Tile) {
                int tileId = ((Tile) actionEvent.getSource()).ID;
                if (model.areAdjacent(0, tileId)) {
                    model.swap(0, tileId);
                    view.getBoardView().moveTile(
                            (Tile) actionEvent.getSource(),
                            model.getRow(tileId),
                            model.getColumn(tileId));
                }
            }
        }
    };

    SlidingPuzzleController(SlidePuzzle view) {
        this.view = view;
        newGame(rows, cols);
    }

    private BoardView createBoardView() {
        BoardView boardView = new BoardView();
        for (Integer i : model.boardState) {
            if (i == SlidingPuzzleModel.EMPTYSPACE) continue;
            Tile t = new Tile(i, tileHandler);
            boardView.add(t, model.getColumn(i), model.getRow(i));
        }
        return boardView;
    }

    void moveTile(Tile tile, int row, int col) {
        view.getBoardView().moveTile(tile, row, col);
    }

    void newGame(int rows, int cols) {
        model = new SlidingPuzzleModel(rows, cols);
        model.shuffle();
        view.setBoardView(createBoardView());
    }
}
