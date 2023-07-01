package Controller;

import Model.Barang;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class RecentCardController {

    @FXML
    private HBox BaseHBox;

    @FXML
    private Label RecentDetail;

    @FXML
    private ImageView RecentGambarProdukDisplay;

    @FXML
    private Label RecentHargaProdukDisplay;

    @FXML
    private Label RecentNamaProdukDisplay;

    @FXML
    private Label RecentUserProdukDisplay;

    public void setData(Barang barang) {
        Image image = new Image(getClass().getResourceAsStream(barang.getGambar()));
        RecentGambarProdukDisplay.setImage(image);

        RecentNamaProdukDisplay.setText(barang.getNamaBarang());
        RecentHargaProdukDisplay.setText(barang.getHargaBarang());
        RecentUserProdukDisplay.setText(barang.getUser());
    }
}
