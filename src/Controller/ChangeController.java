package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ChangeController implements Initializable{

    @FXML
    private Button Batal;

    @FXML
    private Pane CardChange;

    @FXML
    private Pane OverlayBackground;

    @FXML
    private TextField Replacement;

    @FXML
    private Button Ubah;

    @FXML
    private Label UbahSubject;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        FadeTransition Overlay = new FadeTransition(Duration.millis(100), OverlayBackground);
        Overlay.setFromValue(0.0);
        Overlay.setToValue(0.4);
    }

    @FXML
    void BatalClick(MouseEvent event) {
        Stage stage = (Stage) OverlayBackground.getScene().getWindow();
        stage.close();
    }

    @FXML
    void UbahClick(MouseEvent event) {

    }

    @FXML
    void SetUbahSubject(String X){
        UbahSubject.setText(X);
    }

}
