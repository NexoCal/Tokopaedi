package Controller;



import java.io.IOException;

import Model.Barang;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

        this.ID = barang.getDecoyID();
    }

    @FXML
    void GoToPage(MouseEvent event) throws IOException {
        Barang barang = new Barang();
        barang.setID(ID);
        Parent root = FXMLLoader.load(getClass().getResource("/GraphicUserInterface/KatalogPage.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Tokopaedi");
        stage.show();

        // new FadeIn(root).setSpeed(4.0).play();

        ((Parent) event.getSource()).getScene().getWindow().hide();

    }

}
