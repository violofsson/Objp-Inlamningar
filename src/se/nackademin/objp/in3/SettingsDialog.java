package se.nackademin.objp.in3;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.util.Pair;

public class SettingsDialog extends Dialog<Pair<Integer, Integer>> {
    private SettingsController ctrl;
    private static String stylesheet = "style.css";

    SettingsDialog(int oldRows, int oldCols) throws Exception {
        super();
        this.setTitle("Inställningar");
        this.setHeaderText(null);
        this.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("settingsdialog.fxml"));
        Parent grid = loader.load();
        this.ctrl = loader.getController();
        ctrl.setDefaults(oldRows, oldCols);
        setResultConverter(buttonType -> ctrl.changeSettings(buttonType));
        getDialogPane().setContent(grid);
        //getDialogPane().getStylesheets().add(stylesheet);*/
    }
}
