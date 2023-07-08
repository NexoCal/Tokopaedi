package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Model.Barang;
import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CheckOutPageController implements Initializable{
    DatabaseModel DB = new DatabaseModel();

    @FXML
    private Label AlamatLengkap;

    @FXML
    private Label AlamatUtama;

    @FXML
    private ImageView Gambar;

    @FXML
    private Label HargaProduk;

    @FXML
    private Label NamaBarang;

    @FXML
    private Label NamaDanNomor;

    @FXML
    private Label NamaUser;

    @FXML
    private Label NomorPenjual;

    @FXML
    private Button PesanSekarang;

    @FXML
    private VBox PilihPembayar;

    @FXML
    private Label Total;

    @FXML
    private Label UkuranBerat;

    @FXML
    private HBox Userdetail;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        DB.ConnectToDataBase("src/TokopaediDatabase.db");
        Barang IDBarang = new Barang();
        Barang barang = DB.SelectBarang(IDBarang.getID());
        User IDUser = new User();
        User user = DB.SelectUser(IDUser.getID());

        NamaBarang.setText(barang.getNamaBarang());
        NomorPenjual.setText(barang.getUser());
        NamaDanNomor.setText(user.getNama() +" | "+user.getNomor());
        HargaProduk.setText("Rp " + barang.getHargaBarang());
        Gambar.setImage(barang.getGambar());
        Total.setText("Rp "+HargaProduk.getText().replace("R", "").replace("p", "").replace(" ", ""));
        
    }

    @FXML
    void MenuTrigger(MouseEvent event) {

    }

    @FXML
    void Pesan(MouseEvent event) {

    }

    

}
