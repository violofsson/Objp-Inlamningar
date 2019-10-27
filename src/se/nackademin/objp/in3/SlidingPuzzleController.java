package se.nackademin.objp.in3;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

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
            if (actionEvent.getSource() instanceof Button) {
                //Hämta pusselbitens värde tileId
                //if (model.areAdjacent(0, tileId)
                //model.swap(0, tileId)
                //uppdatera vyn
            }
        }
    };

    SlidingPuzzleController() {
        view = new SlidePuzzle();
        newGame(rows, cols);
    }

    void newGame(int rows, int cols) {
        model = new SlidingPuzzleModel(rows, cols);
        model.shuffle();
        //createBoardView()?
        //view.setBoardView()?
    }

    void createBoardView() {
        GridPane boardView = new GridPane();
        for (Integer i : model.boardState) {
            if (i == 0) continue;
            Button btn = new Button(i.toString());
            btn.setOnAction(tileHandler);
            boardView.add(btn, model.getColumn(i), model.getRow(i));
        }
        //view.setBoardView(boardView)?
    }

    void moveTile(Button tile, int row, int col) {
        //view.moveTile()
    }
}
