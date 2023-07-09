package Controller;

import javax.print.attribute.standard.MediaSize.NA;

import Model.Barang;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class SearchCardController {
    int ID;

    @FXML
    private HBox BaseHBox;

    @FXML
    private ImageView Gambar;

    @FXML
    private Label Harga;

    @FXML
    private Label Kategori;

    @FXML
    private Label Nama;

    @FXML
    private Label Penjual;

    void setCard(Barang barang){
        Gambar.setImage(barang.getGambar());
        Kategori.setText(barang.getKategoriBarang());
        Nama.setText(barang.getNamaBarang());
        Penjual.setText(barang.getUser());
        Harga.setText("Rp "+barang.getHargaBarang());

        this.ID = barang.getID();
    }

}
