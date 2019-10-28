package se.nackademin.objp.in3;

import javafx.scene.layout.GridPane;

public class BoardView extends GridPane {
    public BoardView() {
        super();
        this.getStyleClass().add("board-view");
    }

    void placeTile(TileButton t, int row, int col) {
        if (super.getChildren().contains(t)) {
            setConstraints(t, col, row);
        } else {
            add(t, col, row);
            setFillHeight(t, true);
            setFillWidth(t, true);
        }
    }
}
