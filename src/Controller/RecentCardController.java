package Controller;

import java.io.IOException;

import org.sqlite.core.DB;

import Model.Barang;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RecentCardController {
    DatabaseModel DB = new DatabaseModel();
    int ID;

    @FXML
    private HBox BaseHBox;

    @FXML
    private Label Detail;

    @FXML
    private Label DetailRecent;

    @FXML
    private Label Penjual;

    @FXML
    private Label PenjualRecent;

    @FXML
    private ImageView RecentGambarProdukDisplay;

    @FXML
    private Label RecentHargaProdukDisplay;

    @FXML
    private Label RecentNamaProdukDisplay;

    public void setDataBarang(int ID){
        DB.ConnectToDataBase("src/TokopaediDatabase.db");
        Barang CurrentBarangData = DB.SelectBarang(ID);

        RecentNamaProdukDisplay.setText(CurrentBarangData.getNamaBarang());
        Detail.setText(CurrentBarangData.getUkuranBarang()+"/"+CurrentBarangData.getWarnaBarang());
        RecentHargaProdukDisplay.setText("Rp "+CurrentBarangData.getHargaBarang());
        PenjualRecent.setText(CurrentBarangData.getUser());
        RecentGambarProdukDisplay.setImage(CurrentBarangData.getGambar());
        this.ID = ID;
    }

    @FXML
    void GoToItem(MouseEvent event) throws IOException {
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
