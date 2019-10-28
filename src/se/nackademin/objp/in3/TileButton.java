package se.nackademin.objp.in3;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class TileButton extends Button {
    public final int ID;
    TileButton(Integer i, EventHandler<ActionEvent> handler) {
        super(i.toString());
        ID = i;
        this.setOnAction(handler);
        this.getStyleClass().add("tile");
    }
}
