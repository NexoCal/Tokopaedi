package Controller;

import java.io.FileNotFoundException;
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
    boolean PilihPembayarSudah = false;

    @FXML
    private Label AlamatLengkap;

    @FXML
    private Label AlamatUtama;

    @FXML
    private Pane Body;

    @FXML
    private Pane CardPesanDiterima;

    @FXML
    private Label Diskon;

    @FXML
    private ImageView Gambar;

    @FXML
    private Label HargaProduk;

    @FXML
    private Button Lanjutberburu;

    @FXML
    private Label NamaBarang;

    @FXML
    private Label NamaBarangDiPesan;

    @FXML
    private Label NamaDanNomor;

    @FXML
    private Label NomorPenjual;

    @FXML
    private Pane Overlay;

    @FXML
    private Label Pengiriman;

    @FXML
    private ImageView PesanDiTerimaGambar;

    @FXML
    private Label PesanDiTerimaTotal;

    @FXML
    private Button PesanSekarang;

    @FXML
    private VBox PilihPembayar;

    @FXML
    private Pane Tail;

    @FXML
    private Label Total;

    @FXML
    private Label UkuranBerat;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        DB.ConnectToDataBase("src/TokopaediDatabase.db");
        Barang IDBarang = new Barang();
        Barang barang = DB.SelectBarang(IDBarang.getID());
        User IDUser = new User();
        User user = DB.SelectUser(IDUser.getID());

        String [] alamat = user.getAlamat().split(" \\| ");

        NamaBarang.setText(barang.getNamaBarang());
        NomorPenjual.setText(barang.getUser());
        NamaDanNomor.setText(user.getNama() +" | "+user.getNomor());
        HargaProduk.setText("Rp " + barang.getHargaBarang());
        AlamatUtama.setText(alamat[0]);
        AlamatLengkap.setText(alamat[1]);
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
    void Pesan(MouseEvent event) throws FileNotFoundException, IOException {
        DB.ConnectToDataBase("src/TokopaediDatabase.db");
        Barang IDBarang = new Barang();
        Barang barang = DB.SelectBarang(IDBarang.getID());
        DB.UpdateStatusBarang(barang.getID(), "Pending");

        PesanDiTerimaGambar.setImage(barang.getGambar());;
        PesanDiTerimaTotal.setText("Rp "+Total.getText().replace("Rp ", ""));


        PauseTransition pause = new PauseTransition(Duration.millis(30000));
        TranslateTransition tailer = new TranslateTransition(Duration.millis(1000), Tail);
        TranslateTransition bodyier = new TranslateTransition(Duration.millis(1200), Body);
        TranslateTransition overlayer = new TranslateTransition(Duration.millis(1000), Overlay);

        TranslateTransition GoToDiterimaCard = new TranslateTransition(Duration.millis(2000), CardPesanDiterima);
        GoToDiterimaCard.setInterpolator(Custom1);
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
        
        GoToDiterimaCard.setDelay(Duration.millis(300));
        GoToDiterimaCard.setInterpolator(Custom1);
        GoToDiterimaCard.setToX(1280);
        
        bodyier.setOnFinished(e -> {pause.play();Overlaygone();});
        

        GoToDiterimaCard.play();
        tailer.play();
        overlayer.play();
        bodyier.play();



    }

    private void Overlaygone(){
        
        TranslateTransition tailer = new TranslateTransition(Duration.millis(1000), Tail);
        TranslateTransition bodyier = new TranslateTransition(Duration.millis(1200), Body);

        tailer.setInterpolator(Custom1);
        bodyier.setInterpolator(Custom1);
               
        tailer.setToX(2560);
        tailer.setDelay(Duration.millis(250));
        bodyier.setToX(2560);
        bodyier.setDelay(Duration.millis(150));


        tailer.play();
        bodyier.play();

    }

    @FXML
    void DipilihPembayaran(MouseEvent event) {
        if(!PilihPembayarSudah){
            PilihPembayar.setStyle("-fx-border-color: #403086; -fx-border-width: 3;");
            PilihPembayarSudah = true;
        }else{
            PilihPembayar.setStyle("-fx-border-color: ; -fx-border-width: 1;");
            PilihPembayarSudah = false;
        }
    }

    @FXML
    void BackToMain(MouseEvent event) throws IOException {

        DB.ConnectToDataBase("src/TokopaediDatabase.db");
        Barang IDBarang = new Barang();
        Barang barang = DB.SelectBarang(IDBarang.getID());
        DB.Deletebarang(barang.getID());
        DB.DisconnectFromDataBase();
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

    @FXML
    void GoToMenu(MouseEvent event) throws IOException {
        
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
