package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Barang;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class KatalogPageController implements Initializable {

    DatabaseModel DB = new DatabaseModel();
    Barang idBarang = new Barang();

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

    @FXML
    private ImageView Gambar;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        DB.ConnectToDataBase("src/TokopaediDatabase.db");
        Barang barang = DB.SelectBarang(idBarang.getID());

        Nama.setText(barang.getNamaBarang());
        Harga.setText("Rp "+barang.getHargaBarang());
        DetailKondisi.setText(barang.getKondisi());
        DetailBrand.setText(barang.getBrandBarang());
        DetailWarna.setText(barang.getWarnaBarang());
        DetailBerat.setText(barang.getUkuranBarang());
        DetailPengunaan.setText("Deskripsi       : " + barang.getDeskripsiBarang());
        Gambar.setImage(barang.getGambar());
        Penjual.setText(barang.getUser());
    }

    @FXML
    void BeliSekarang(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GraphicUserInterface/CheckOutPage.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Tokopaedi");
        stage.show();

        ((Parent) event.getSource()).getScene().getWindow().hide();
    }

}
