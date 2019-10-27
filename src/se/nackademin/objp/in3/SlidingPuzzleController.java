package se.nackademin.objp.in3;

public class SlidingPuzzleController {
    SlidePuzzle view;
    SlidingPuzzleModel model;

    // Lyssnare för nytt spel
    // Lyssnare för inställningar
    // Lyssnare för avslutning?

    // Lyssnare för pusselbitar - de delar på en och identifieras
    // med motsvarande bitar i modellen

    SlidingPuzzleController() {
        view = new SlidePuzzle();
        newGame(4, 4);
    }

    void newGame(int rows, int cols) {
        model = new SlidingPuzzleModel(rows, cols);
        //Skapa GridPane här eller i vyn?
        //view.setNågonting
    }


}
