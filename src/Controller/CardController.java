package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class CardController implements Initializable{

    @FXML
    private ImageView GambarBarangDisplay;

    @FXML
    private Label HargaDisplay;

    @FXML
    private Label Keterangan;

    @FXML
    private Label NamaBarangDisplay;

    @FXML
    private Label NamaUserDisplay;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        throw new UnsupportedOperationException("Unimplemented method 'initialize'");
    }

}