package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Barang;
import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class KatalogCardController implements Initializable {
    DatabaseModel DB = new DatabaseModel();
    User session = new User();
    boolean same = false;
    static boolean showEdit = false;
    int ID;

    @FXML
    private VBox BaseVBox;

    @FXML
    private ImageView Gambar;

    @FXML
    private Label Harga;

    @FXML
    private Label Nama;

    @FXML
    private Label Ukuran;

    @FXML
    private Pane EditButton;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        EditButton.setVisible(true);
    }

    public void setData(int ID){
        DB.ConnectToDataBase("src/TokopaediDatabase.db");
        Barang barang = DB.SelectBarang(ID);
        User CurrentUser = DB.SelectUser(session.getID());

        if (barang.getUser().equals(CurrentUser.getNama())){
            Nama.setText(barang.getNamaBarang());
            Ukuran.setText("Ukuran "+barang.getUkuranBarang());
            Harga.setText("Rp "+barang.getHargaBarang());
            Gambar.setImage(barang.getGambar());
            this.ID = ID;
            same = true;
        }else{
            same = false;
        }

    }

    @FXML
    void GoToEdit(MouseEvent event) throws IOException {
        KatalogEditDeletePageController katalogEditDeletePageController = new KatalogEditDeletePageController();
        katalogEditDeletePageController.setIDBarang(ID);
    
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GraphicUserInterface/KatalogEditDeletePage.fxml"));
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
