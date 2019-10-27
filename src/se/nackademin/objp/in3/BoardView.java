package se.nackademin.objp.in3;

import javafx.scene.layout.GridPane;

public class BoardView extends GridPane {
    void placeTile(TileButton t, int row, int col) {
        if (super.getChildren().contains(t)) {
            setConstraints(t, col, row);
        } else {
            add(t, col, row);
        }
    }
}
