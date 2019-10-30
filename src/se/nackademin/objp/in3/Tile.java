package se.nackademin.objp.in3;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Tile extends Button {
    public final int ID;

    Tile(Integer i, EventHandler<ActionEvent> handler, int row, int col) {
        super(i.toString());
        ID = i;
        this.setOnAction(handler);
        GridPane.setFillHeight(this, true);
        GridPane.setFillWidth(this, true);
        setPrefSize(1000, 1000);
        //setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.getStyleClass().add("tile");
        GridPane.setConstraints(this, col, row);
    }

    void move(int row, int col) {
        GridPane.setConstraints(this, col, row);
    }
}
