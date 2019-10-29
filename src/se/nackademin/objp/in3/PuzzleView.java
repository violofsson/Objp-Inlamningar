package se.nackademin.objp.in3;

import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.util.List;
import java.util.Optional;

public class PuzzleView extends BorderPane {
    private static String stylesheet = "style.css";
    private GridPane boardView = new GridPane();
    private Text msg = new Text(" ");

    static class SettingsDialog extends Dialog<Pair<Integer, Integer>> {
        SettingsDialog(int oldRows, int oldCols) {
            super();
            this.setTitle("Inställningar");
            this.setHeaderText(null);
            this.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

            Integer[] sizes = {2, 3, 4, 5, 6, 7, 8, 9};
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
            getDialogPane().getStylesheets().add(stylesheet);

            setResultConverter(buttonType -> {
                if (buttonType == ButtonType.OK) {
                    return new Pair<>(rowsCombo.getValue(),
                            colsCombo.getValue());
                }
                else return null;
            });
        }
    }

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

    Optional<Pair<Integer, Integer>> changeSettings(int oldRows, int oldCols) {
        SettingsDialog dialog = new SettingsDialog(oldRows, oldCols);
        return dialog.showAndWait();
    }

    void setBoardDisabled(boolean b) {
        boardView.setDisable(b);
    }

    /*protected GridPane getBoardView() {
        return boardView;
    }*/

    protected void resetBoard(List<Tile> newBoard) {
        boardView.getChildren().clear();
        boardView.getChildren().addAll(newBoard);
        setBoardDisabled(false);
    }

    /*protected void setBoardView(GridPane boardView) {
        this.boardView = boardView;
        this.setCenter(boardView);
    }*/

    void setMessage(String s) {
        msg.setText(s);
    }
}
