package se.nackademin.objp.in3;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.util.List;
import java.util.Optional;

class SettingsDialog extends Dialog<Pair<Integer, Integer>> {
    SettingsController ctrl;

    SettingsDialog(int oldRows, int oldCols) throws Exception {
        super();
        this.setTitle("Inställningar");
        this.setHeaderText(null);
        this.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("settingsdialog.fxml"));
        Parent grid = loader.load();
        this.ctrl = loader.getController();
        ctrl.setDefaults(oldRows, oldCols);
        getDialogPane().setContent(grid);

            /*Integer[] sizes = {2, 3, 4, 5, 6, 7, 8, 9};
            ComboBox<Integer> rowsCombo = new ComboBox<>();
            rowsCombo.getItems().addAll(sizes);
            rowsCombo.getSelectionModel().select((Integer) oldRows);
            ComboBox<Integer> colsCombo = new ComboBox<>();
            colsCombo.getItems().addAll(sizes);
            colsCombo.getSelectionModel().select((Integer) oldCols);

            Label rowsLabel = new Label("Rader:");
            rowsLabel.setLabelFor(rowsCombo);
            Label colsLabel = new Label("Kolumner:");
            colsLabel.setLabelFor(colsCombo);

            GridPane grid = new GridPane();
            grid.addRow(0, rowsLabel, rowsCombo);
            grid.addRow(1, colsLabel, colsCombo);

            getDialogPane().setContent(grid);
            getDialogPane().getStylesheets().add(stylesheet);*/

        setResultConverter(buttonType -> {
            if (buttonType == ButtonType.OK) {
                return new Pair<>(ctrl.rowsBox.getValue(),
                        ctrl.colsBox.getValue());
            }
            else return null;
        });
    }
}

public class PuzzleView extends BorderPane {
    private static String stylesheet = "style.css";
    private GridPane boardView = new GridPane();
    private Text msg = new Text(" ");


    PuzzleView(PuzzleController controller) {
        super();
        Button newGameButton = new Button("Nytt spel");
        newGameButton.setOnAction(controller.newGameHandler);
        Button settingsButton = new Button("Inställningar");
        settingsButton.setOnAction(controller.settingsHandler);
        Button exitButton = new Button("Avsluta");
        exitButton.setOnAction(controller.exitHandler);

        HBox buttonPane = new HBox();
        buttonPane.getChildren().addAll(newGameButton, settingsButton, exitButton);
        buttonPane.getStyleClass().add("button-pane");

        boardView.getStyleClass().add("board-view");
        setTop(buttonPane);
        setBottom(msg);
        setCenter(boardView);
        getStylesheets().setAll(stylesheet);
    }

    static Optional<Pair<Integer, Integer>> changeSettings(int oldRows, int oldCols) throws Exception {
        SettingsDialog dialog = new SettingsDialog(oldRows, oldCols);
        return dialog.showAndWait();
    }

    protected void resetBoard(List<Tile> newBoard) {
        boardView.getChildren().clear();
        boardView.getChildren().addAll(newBoard);
        setBoardDisabled(false);
    }

    void setBoardDisabled(boolean b) {
        boardView.setDisable(b);
    }

    void setMessage(String s) {
        msg.setText(s);
    }
}
