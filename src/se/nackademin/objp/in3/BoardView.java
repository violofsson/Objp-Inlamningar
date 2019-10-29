package se.nackademin.objp.in3;

import javafx.scene.layout.GridPane;

public class BoardView extends GridPane {
    public BoardView() {
        super();
        this.getStyleClass().add("board-view");
    }

    void addTile(Tile t, int row, int col) {
        add(t, col, row);
        setFillWidth(t, true);
        setFillHeight(t, true);
    }

    void moveTile(Tile t, int row, int col) {
        if (super.getChildren().contains(t)) {
            setConstraints(t, col, row);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
