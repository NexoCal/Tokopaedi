package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Model.Barang;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class KatalogPageController implements Initializable {

    DatabaseModel DB = new DatabaseModel();

    @FXML
    private Button ButtonBeli;

    @FXML
    private Button ButtonHubung;

    @FXML
    private Label DetailBerat;

    @FXML
    private Label DetailBrand;

    @FXML
    private Label DetailKondisi;

    @FXML
    private Label DetailPengunaan;

    @FXML
    private Label DetailWarna;

    @FXML
    private Label Harga;

    @FXML
    private Label Nama;

    @FXML
    private Label Penjual;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        DB.ConnectToDataBase("src/TokopaediDatabase.db");
        Barang barang = new Barang();
        barang.setNamaBarang(DB.GetProductDataNama(barang.getID()));
        Nama.setText(barang.getNamaBarang());
        barang.setHargaBarang(DB.GetProductDataHarga(barang.getID()));
        Harga.setText(barang.getHargaBarang());
        barang.setUser(DB.GetProductDataPenjual(barang.getID()));
        Penjual.setText(barang.getUser());
    }

}
