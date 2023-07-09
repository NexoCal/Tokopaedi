package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Barang;
import Model.SceneTracker;
import Model.User;
import animatefx.animation.ZoomIn;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class KatalogPageController implements Initializable {

    DatabaseModel DB = new DatabaseModel();
    Barang idBarang = new Barang();
    User idUser = new User();

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

    @FXML
    private Label NamaUser;

    @FXML
    private Pane UserMenu;

    Boolean MenuOn = false;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        DB.ConnectToDataBase("src/TokopaediDatabase.db");
        Barang barang = DB.SelectBarang(idBarang.getID());
        User CurrentUser = DB.SelectUser(idUser.getID());
        SceneTracker track = new SceneTracker();
        track.setTrack(false);

        Nama.setText(barang.getNamaBarang());
        Harga.setText("Rp "+barang.getHargaBarang());
        DetailKondisi.setText(barang.getKondisi());
        DetailBrand.setText(barang.getBrandBarang());
        DetailWarna.setText(barang.getWarnaBarang());
        DetailBerat.setText(barang.getUkuranBarang());
        DetailPengunaan.setText("Deskripsi       : " + barang.getDeskripsiBarang());
        Gambar.setImage(barang.getGambar());
        Penjual.setText(barang.getUser());
        NamaUser.setText(CurrentUser.getNama());

        DB.DisconnectFromDataBase();
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

    @FXML
    void MenuTrigger(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GraphicUserInterface/UserMenu.fxml"));
        Pane Menu = loader.load();
        if (MenuOn) {
            UserMenu.getChildren().clear();
            MenuOn = false;
        } else {
            UserMenu.getChildren().add(Menu);
            MenuOn = true;
        }

    }
    
    @FXML
    void GoToMain(MouseEvent event) throws IOException {
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
