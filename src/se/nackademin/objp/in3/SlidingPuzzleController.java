package se.nackademin.objp.in3;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SlidingPuzzleController {
    SlidingPuzzleView view;
    SlidingPuzzleModel model;
    private int rows = 4;
    private int cols = 4;
    private boolean gameOver = false;

    // Lyssnare för nytt spel
    EventHandler<ActionEvent> newGameHandler = actionEvent -> newGame(rows, cols);

    // Lyssnare för inställningar

    // Lyssnare för avslutning?
    EventHandler<ActionEvent> exitHandler = actionEvent -> System.exit(0);

    // Lyssnare för pusselbitar - de delar på en och identifieras
    // med motsvarande bitar i modellen
    EventHandler<ActionEvent> tileHandler = new EventHandler<>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            if (!gameOver) {
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
            }
        }
    };

    SlidingPuzzleController() {
        view = new SlidingPuzzleView(this);
        newGame(rows, cols);
    }

    void checkWinning() {
        if (model.isSolved()) {
            gameOver = true;
            // Meddelande om vinst
        }
    }

    private BoardView createBoardView() {
        BoardView boardView = new BoardView();
        for (Integer i : model.boardState) {
            if (i == SlidingPuzzleModel.EMPTYSPACE) continue;
            Tile t = new Tile(i, tileHandler);
            boardView.placeTile(t, model.getRow(i), model.getColumn(i));
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
    }
}
