package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Barang;
import Model.User;
import animatefx.animation.ZoomIn;
import javafx.animation.Interpolator;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

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

    @FXML
    private Label Diskon;

    @FXML
    private Label Pengiriman;
    
    @FXML
    private Pane PesanDiTerima;

    @FXML
    private Label PesanDiTerimaDiskon;

    @FXML
    private ImageView PesanDiTerimaGambar;

    @FXML
    private Label PesanDiTerimaHarga;

    @FXML
    private Label PesanDiTerimaTotal;

    @FXML
    private Label PesanDiTerimaPengiriman;

    @FXML
    private VBox CardPesanDiterima;

    @FXML
    private Button Lanjutberburu;

    @FXML
    private Pane Overlay;

    @FXML
    private Pane Tail;

    @FXML
    private Pane Body;


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

    Interpolator Custom1 = new Interpolator() {
        @Override
        protected double curve(double e) {
            return (e == 1.0) ? 1.0 : 1 - Math.pow(2.0, -10 * e);
        }
    };

    @FXML
    void Pesan(MouseEvent event) {
        DB.ConnectToDataBase("src/TokopaediDatabase.db");
        Barang IDBarang = new Barang();
        Barang barang = DB.SelectBarang(IDBarang.getID());

        PesanDiTerimaDiskon.setText("Rp " + Diskon.getText().replace("Rp ", ""));
        PesanDiTerimaGambar.setImage(barang.getGambar());;
        PesanDiTerimaPengiriman.setText("Rp "+Pengiriman.getText().replace("Rp ", ""));
        PesanDiTerimaHarga.setText("Rp "+HargaProduk.getText().replace("Rp ", ""));
        PesanDiTerimaTotal.setText("Rp "+Total.getText().replace("Rp ", ""));

        PauseTransition pause = new PauseTransition(Duration.millis(30000));
        TranslateTransition tailer = new TranslateTransition(Duration.millis(1000), Tail);
        TranslateTransition bodyier = new TranslateTransition(Duration.millis(1200), Body);
        TranslateTransition overlayer = new TranslateTransition(Duration.millis(1000), Overlay);

        TranslateTransition GoToDiterima = new TranslateTransition(Duration.millis(1700), PesanDiTerima);
        TranslateTransition GoToDiterimaCard = new TranslateTransition(Duration.millis(2000), CardPesanDiterima);
        TranslateTransition ButtonDiTerima = new TranslateTransition(Duration.millis(2300), Lanjutberburu);
        GoToDiterima.setInterpolator(Custom1);
        tailer.setInterpolator(Custom1);
        bodyier.setInterpolator(Custom1);
        overlayer.setInterpolator(Custom1);

        tailer.setToX(1280);
        bodyier.setDelay(Duration.millis(150));
        bodyier.setToX(1280);
        overlayer.setToX(1280);

        tailer.setToX(1280);
        bodyier.setDelay(Duration.millis(150));
        bodyier.setToX(1280);
        
        GoToDiterima.setToY(-800);
        GoToDiterimaCard.setDelay(Duration.millis(100));
        GoToDiterimaCard.setInterpolator(Custom1);
        GoToDiterimaCard.setToY(-800);
        ButtonDiTerima.setDelay(Duration.millis(50));
        ButtonDiTerima.setInterpolator(Custom1);
        ButtonDiTerima.setToY(-800);
        
        bodyier.setOnFinished(e -> {pause.play();Overlaygone();});
        

        GoToDiterima.play();
        GoToDiterimaCard.play();
        ButtonDiTerima.play();
        tailer.play();
        overlayer.play();
        bodyier.play();



    }

    private void Overlaygone(){
        
        TranslateTransition tailer = new TranslateTransition(Duration.millis(1000), Tail);
        TranslateTransition bodyier = new TranslateTransition(Duration.millis(1200), Body);
        TranslateTransition overlayer = new TranslateTransition(Duration.millis(1000), Overlay);

        tailer.setInterpolator(Custom1);
        bodyier.setInterpolator(Custom1);
        overlayer.setInterpolator(Custom1);

       
        
        tailer.setToX(2560);
        tailer.setDelay(Duration.millis(250));
        bodyier.setToX(2560);
        bodyier.setDelay(Duration.millis(150));
        overlayer.setToX(2560);
        overlayer.setDelay(Duration.millis(150));

        tailer.play();
        overlayer.play();
        bodyier.play();

    }

    @FXML
    void BackToMain(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GraphicUserInterface/MainScreen.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

            stage.setScene(scene);
            stage.setResizable(false);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("Tokopaedi");
            stage.show();

            new ZoomIn(root).setSpeed(2.2).play();

            ((Parent) event.getSource()).getScene().getWindow().hide();

    }

    

}
