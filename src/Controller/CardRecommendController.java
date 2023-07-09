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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CardRecommendController {
    DatabaseModel DB = new DatabaseModel();
    int ID;

    @FXML
    private ImageView GambarProduk;

    @FXML
    private Label HargaBarang;

    @FXML
    private Label NamaBarang;

    @FXML
    private Label UkuranBarang;

    @FXML
    private VBox Card;
    
    public void setDataBarang(int ID){
        DB.ConnectToDataBase("src/TokopaediDatabase.db");
        Barang CurrentBarangData = DB.SelectBarang(ID);

        NamaBarang.setText(CurrentBarangData.getNamaBarang());
        UkuranBarang.setText("Ukuran/Berat "+CurrentBarangData.getUkuranBarang());
        HargaBarang.setText("Rp "+CurrentBarangData.getHargaBarang());
        GambarProduk.setImage(CurrentBarangData.getGambar());
        this.ID = ID;
    }

    public void setDataBarang(Barang barang){
        NamaBarang.setText(barang.getNamaBarang());
        UkuranBarang.setText("Ukuran/Berat "+barang.getUkuranBarang());
        HargaBarang.setText("Rp "+barang.getHargaBarang());
        GambarProduk.setImage(barang.getGambar());
        this.ID = barang.getID();
    }

    @FXML
    void BukaKatalogPage(MouseEvent event) throws IOException {
        Barang barang = new Barang();
        barang.setID(ID);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GraphicUserInterface/KatalogPage.fxml"));
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


