package se.nackademin.objp.in3;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SlidingPuzzleController {
    private SlidingPuzzleView view;
    private SlidingPuzzleModel model;
    private int rows = 4;
    private int cols = 4;

    // TODO Lyssnare för inställningar
    EventHandler<ActionEvent> newGameHandler = actionEvent -> newGame(rows, cols);
    EventHandler<ActionEvent> exitHandler = actionEvent -> System.exit(0);

    EventHandler<ActionEvent> tileHandler = new EventHandler<>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            if (actionEvent.getSource() instanceof TileButton) {
                int tileId = ((TileButton) actionEvent.getSource()).ID;
                if (model.areAdjacent(0, tileId)) {
                    model.swap(0, tileId);
                    view.getBoardView().placeTile(
                            (TileButton) actionEvent.getSource(),
                            model.getRow(tileId),
                            model.getColumn(tileId));
                }
                checkWinning();
            }
        }
    };

    SlidingPuzzleController() {
        view = new SlidingPuzzleView(this);
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
            if (i == SlidingPuzzleModel.EMPTYSPACE) continue;
            TileButton tb = new TileButton(i, tileHandler);
            boardView.placeTile(tb, model.getRow(i), model.getColumn(i));
        }
        return boardView;
    }

    SlidingPuzzleView getView() {
        return view;
    }

    void newGame(int rows, int cols) {
        model = new SlidingPuzzleModel(rows, cols);
        model.shuffle();
        view.setBoardView(createBoardView());
        view.setMessage(rows + "x" + cols);
    }
}
