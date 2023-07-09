package Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import animatefx.animation.AnimationFX;


import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class KatalogPageAddController implements Initializable {

    @FXML
    private Button BatalButton;

    @FXML
    private TextField FieldBrandBarang;

    @FXML
    private TextArea FieldDeskripsi;

    @FXML
    private TextField FieldHargaBarang;

    @FXML
    private TextField FieldNamaBarang;

    @FXML
    private TextField FieldUkuranBarang;

    @FXML
    private TextField FieldWarnaBarang;

    @FXML
    private ImageView GambarBarang;

    @FXML
    private Button KategoriElektronik;

    @FXML
    private Button KategoriFashion;

    @FXML
    private Button KategoriHobi;

    @FXML
    private Button KategoriOtomotif;

    @FXML
    private Button TambahButton;

    @FXML
    private ChoiceBox<String> PilihKondisi;

    private Button SelectedKategori;

    private String[] Kondisi = { "Seperti Baru", "Layak Pakai", "Tua" };
    private String PilihanKategori;
    Image DataGambarBarang;
    File DataGambarBarangSimpan;

    DatabaseModel DB = new DatabaseModel();
    User session = new User();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        DB.ConnectToDataBase("src/TokopaediDatabase.db");
        PilihKondisi.getItems().addAll(Kondisi);
        User DataCurrentUser = DB.SelectUser(session.getID());
        session = DataCurrentUser;
        DB.DisconnectFromDataBase();
    }

    @FXML
    void Batal(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GraphicUserInterface/KatalogManagePage.fxml"));
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
    void ElektronikPilih(MouseEvent event) {
        if (SelectedKategori != null) {
            SelectedKategori.setStyle("-fx-border-radius: 0;\r\n" + //
                    "    -fx-background-radius: 0;\r\n" + //
                    "    -fx-background-color: #ffffff;\r\n" + //
                    "    -fx-border-color: #000000;\r\n" + //
                    "    -fx-text-fill: #3C394D;\r\n" + //
                    "    -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0.2, 0, 2)");
                    
            KategoriElektronik.setStyle("-fx-border-radius: 0;\r\n" + //
                    "    -fx-background-radius: 0;\r\n" + //
                    "    -fx-background-color: #3C394D;\r\n" + //
                    "    -fx-border-color: #3C394D;\r\n" + //
                    "    -fx-text-fill: #ffffff;\r\n" + //
                    "    -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0.2, 0, 2)");
        }else{
            KategoriElektronik.setStyle("-fx-border-radius: 0;\r\n" + //
                    "    -fx-background-radius: 0;\r\n" + //
                    "    -fx-background-color: #3C394D;\r\n" + //
                    "    -fx-border-color: #3C394D;\r\n" + //
                    "    -fx-text-fill: #ffffff;\r\n" + //
                    "    -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0.2, 0, 2)");
        }
        SelectedKategori = KategoriElektronik;
        PilihanKategori = KategoriElektronik.getText();
    }

    @FXML
    void FashionPilih(MouseEvent event) {
        if (SelectedKategori != null) {
            SelectedKategori.setStyle("-fx-border-radius: 0;\r\n" + //
                    "    -fx-background-radius: 0;\r\n" + //
                    "    -fx-background-color: #ffffff;\r\n" + //
                    "    -fx-border-color: #000000;\r\n" + //
                    "    -fx-text-fill: #3C394D;\r\n" + //
                    "    -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0.2, 0, 2)");
                    
            KategoriFashion.setStyle("-fx-border-radius: 0;\r\n" + //
                    "    -fx-background-radius: 0;\r\n" + //
                    "    -fx-background-color: #3C394D;\r\n" + //
                    "    -fx-border-color: #3C394D;\r\n" + //
                    "    -fx-text-fill: #ffffff;\r\n" + //
                    "    -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0.2, 0, 2)");


        }else{
            KategoriFashion.setStyle("-fx-border-radius: 0;\r\n" + //
                    "    -fx-background-radius: 0;\r\n" + //
                    "    -fx-background-color: #3C394D;\r\n" + //
                    "    -fx-border-color: #3C394D;\r\n" + //
                    "    -fx-text-fill: #ffffff;\r\n" + //
                    "    -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0.2, 0, 2)");

        }
        SelectedKategori = KategoriFashion;
        PilihanKategori = KategoriFashion.getText();

    }

    @FXML
    void GantiGambarBarang(MouseEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(
            new ExtensionFilter("Image Files (.png, .jpg)", "*.png", "*.jpg")
        );

        File imageFile = chooser.showOpenDialog(null);
        if (imageFile != null){
            DataGambarBarang = new Image(imageFile.toPath().toString());
            GambarBarang.setImage(DataGambarBarang);
            DataGambarBarangSimpan = imageFile;
        }
    }

    @FXML
    void HobiPilih(MouseEvent event) {
        if (SelectedKategori != null) {
            SelectedKategori.setStyle("-fx-border-radius: 0;\r\n" + //
                    "    -fx-background-radius: 0;\r\n" + //
                    "    -fx-background-color: #ffffff;\r\n" + //
                    "    -fx-border-color: #000000;\r\n" + //
                    "    -fx-text-fill: #3C394D;\r\n" + //
                    "    -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0.2, 0, 2)");
                    
            KategoriHobi.setStyle("-fx-border-radius: 0;\r\n" + //
                    "    -fx-background-radius: 0;\r\n" + //
                    "    -fx-background-color: #3C394D;\r\n" + //
                    "    -fx-border-color: #3C394D;\r\n" + //
                    "    -fx-text-fill: #ffffff;\r\n" + //
                    "    -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0.2, 0, 2)");
        }else{
            KategoriHobi.setStyle("-fx-border-radius: 0;\r\n" + //
                    "    -fx-background-radius: 0;\r\n" + //
                    "    -fx-background-color: #3C394D;\r\n" + //
                    "    -fx-border-color: #3C394D;\r\n" + //
                    "    -fx-text-fill: #ffffff;\r\n" + //
                    "    -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0.2, 0, 2)");
        }
            SelectedKategori = KategoriHobi;
            PilihanKategori = KategoriHobi.getText();
    }

    @FXML
    void OtomotifPilih(MouseEvent event) {
        if (SelectedKategori != null) {
            SelectedKategori.setStyle("-fx-border-radius: 0;\r\n" + //
                    "    -fx-background-radius: 0;\r\n" + //
                    "    -fx-background-color: #ffffff;\r\n" + //
                    "    -fx-border-color: #000000;\r\n" + //
                    "    -fx-text-fill: #3C394D;\r\n" + //
                    "    -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0.2, 0, 2)");
                    
            KategoriOtomotif.setStyle("-fx-border-radius: 0;\r\n" + //
                    "    -fx-background-radius: 0;\r\n" + //
                    "    -fx-background-color: #3C394D;\r\n" + //
                    "    -fx-border-color: #3C394D;\r\n" + //
                    "    -fx-text-fill: #ffffff;\r\n" + //
                    "    -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0.2, 0, 2)");
        }else{
            KategoriOtomotif.setStyle("-fx-border-radius: 0;\r\n" + //
                    "    -fx-background-radius: 0;\r\n" + //
                    "    -fx-background-color: #3C394D;\r\n" + //
                    "    -fx-border-color: #3C394D;\r\n" + //
                    "    -fx-text-fill: #ffffff;\r\n" + //
                    "    -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0.2, 0, 2)");
        }
        SelectedKategori = KategoriOtomotif;
        PilihanKategori = KategoriOtomotif.getText();

    }

    @FXML
    void TambahBarang(MouseEvent event) throws IOException {
        DB.ConnectToDataBase("src/TokopaediDatabase.db");
        String NamaBarang = FieldNamaBarang.getText();
        String HargaBarang = FieldHargaBarang.getText();
        String KondisiBarang = PilihKondisi.getValue();
        String BrandBarang = FieldBrandBarang.getText();
        String UkuranBarang = FieldUkuranBarang.getText();
        String WarnaBarang = FieldWarnaBarang.getText();
        String KategoriBarang = PilihanKategori;
        String Deskripsi = FieldDeskripsi.getText();
        String PenjualBarang = session.getNama();
        File GambarBarang = DataGambarBarangSimpan;

        if(NamaBarang == null || HargaBarang == null || KategoriBarang == null || KondisiBarang == null|| BrandBarang == null|| UkuranBarang == null|| WarnaBarang == null){

        }else{
        try {
            DB.InsertBarang(NamaBarang,HargaBarang,PenjualBarang,KondisiBarang,UkuranBarang,BrandBarang,WarnaBarang,KategoriBarang,Deskripsi,GambarBarang);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(e);
        }
        DB.DisconnectFromDataBase();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GraphicUserInterface/KatalogManagePage.fxml"));
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

}
