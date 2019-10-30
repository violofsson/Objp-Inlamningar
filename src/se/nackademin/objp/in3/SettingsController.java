package se.nackademin.objp.in3;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.util.Pair;

public class SettingsController {
    @FXML
    public ChoiceBox<Integer> rowsBox;

    @FXML
    public ChoiceBox<Integer> colsBox;

    private Integer[] sizes = {3, 4, 5, 6, 7, 8};

    @FXML
    public void initialize() {
        rowsBox.getItems().setAll(sizes);
        colsBox.getItems().setAll(sizes);
    }

    void setDefaults(int rows, int cols) {
        rowsBox.setValue(rows);
        colsBox.setValue(cols);
    }

    Pair<Integer, Integer> changeSettings(ButtonType buttonType) {
        if (buttonType == ButtonType.OK) {
            return new Pair<>(rowsBox.getValue(),
                    colsBox.getValue());
        } else return null;
    }
}
