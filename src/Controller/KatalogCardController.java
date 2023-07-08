package Controller;

import Model.Barang;
import Model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class KatalogCardController {
    DatabaseModel DB = new DatabaseModel();
    User session = new User();
    boolean same = false;
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

}
