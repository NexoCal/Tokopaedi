package Controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.glavo.png.PNGWriter;
import org.glavo.png.javafx.PNGJavaFXUtils;

import Model.Barang;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.awt.image.BufferedImage;

public class AdminOnlyBarangController implements Initializable {

    DatabaseModel DB = new DatabaseModel();

    @FXML
    private TableColumn<Barang, String> HargaCol;

    @FXML
    private TableColumn<Barang, String> IDCol;

    @FXML
    private TableColumn<Barang, String> NamaCol;

    @FXML
    private TableColumn<Barang, String> PenjualCol;

    @FXML
    private TableColumn<Barang, String> StatusCol;

    @FXML
    private TableView<Barang> TableBarang;

    @FXML
    private TextField FieldSearchHargaHigh;

    @FXML
    private TextField FieldSearchHargaLow;

    @FXML
    private TextField FieldSearchPenjual;

    @FXML
    private Button FilterButton;

    @FXML
    private Label BarangNav;

    @FXML
    private Label KeluarNav;

    @FXML
    private Label UserNav;

    ObservableList<Barang> DataBarang = FXCollections.observableArrayList();

    private int FilterType = 1;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        DB.ConnectToDataBase("src/TokopaediDatabase.db");
        IDCol.setCellValueFactory(new PropertyValueFactory<Barang, String>("DecoyID"));
        NamaCol.setCellValueFactory(new PropertyValueFactory<Barang, String>("NamaBarang"));
        HargaCol.setCellValueFactory(new PropertyValueFactory<Barang, String>("HargaBarang"));
        PenjualCol.setCellValueFactory(new PropertyValueFactory<Barang, String>("User"));
        StatusCol.setCellValueFactory(new PropertyValueFactory<Barang, String>("Status"));

        List<Barang> SemuaBarang = DB.DaftarBarangLengkapAdminVersion();

        for (Barang i : SemuaBarang) {
            DataBarang.add(i);
        }

        TableBarang.setItems(DataBarang);

    }

    @FXML
    void DeleteSelected(MouseEvent event) {

        Barang Barangpilihan = TableBarang.getSelectionModel().getSelectedItem();
        DB.Deletebarang(Barangpilihan.getDecoyID());

        TableBarang.getItems().removeAll(TableBarang.getSelectionModel().getSelectedItem());
    }

    @FXML
    void Filter(MouseEvent event) {
        ObservableList<Barang> FilteredBarang = FXCollections.observableArrayList();
        switch (FilterType) {
            case 1:
                for (Barang i : DataBarang) {
                    if (i.getStatus().equals("Belum Terjual")) {
                        FilteredBarang.add(i);
                    }

                }

                TableBarang.setItems(FilteredBarang);
                TableBarang.refresh();

                FilterType = 2;
                FilterButton.setText("Filter: Belum Terjual");

                break;

            case 2:
                for (Barang i : DataBarang) {
                    if (i.getStatus().equals("Pending")) {
                        FilteredBarang.add(i);
                    }

                }

                TableBarang.setItems(FilteredBarang);
                TableBarang.refresh();

                FilterType = 3;
                FilterButton.setText("Filter: Pending");

                break;

            case 3:
                for (Barang i : DataBarang) {
                    if (i.getStatus().equals("Sudah Terjual")) {
                        FilteredBarang.add(i);
                    }

                }

                TableBarang.setItems(FilteredBarang);
                TableBarang.refresh();

                FilterType = 4;
                FilterButton.setText("Filter: Sudah Terjual");

                break;
            case 4:
                TableBarang.setItems(DataBarang);
                TableBarang.refresh();

                FilterType = 1;
                FilterButton.setText("Filter: All");

                break;
        }

    }

    @FXML
    void Search(MouseEvent event) {
        ObservableList<Barang> SearchedListBarang = FXCollections.observableArrayList();
        String SearchedPenjual = FieldSearchPenjual.getText();
        int LowPoint = 0;
        int HighPoint = -1;
        if (!FieldSearchHargaLow.getText().equals("") && !FieldSearchHargaHigh.getText().equals("")) {
            LowPoint = Integer.valueOf(FieldSearchHargaLow.getText().replace(".", ""));
            HighPoint = Integer.valueOf(FieldSearchHargaHigh.getText().replace(".", ""));
        }

        Boolean IsPossible = LowPoint <= HighPoint;
        if (!SearchedPenjual.equals("") && IsPossible == true) {
            for (Barang i : DataBarang) {
                int Harga = Integer.valueOf(i.getHargaBarang().replace(".", ""));
                if (SearchedPenjual.equals(i.getUser())) {
                    if (LowPoint <= Harga && Harga <= HighPoint)
                        SearchedListBarang.add(i);
                }

            }

            TableBarang.setItems(SearchedListBarang);
            TableBarang.refresh();
        } else if (SearchedPenjual.equals("") && IsPossible == true) {
            for (Barang i : DataBarang) {
                int Harga = Integer.valueOf(i.getHargaBarang().replace(".", ""));
                if (LowPoint <= Harga && Harga <= HighPoint)
                    SearchedListBarang.add(i);

            }
            TableBarang.setItems(SearchedListBarang);
            TableBarang.refresh();

        } else if (!SearchedPenjual.equals("")) {
            for (Barang i : DataBarang) {
                if (SearchedPenjual.equals(i.getUser())) {
                    SearchedListBarang.add(i);
                }

            }

            TableBarang.setItems(SearchedListBarang);
            TableBarang.refresh();

        } else {
            TableBarang.setItems(DataBarang);
            TableBarang.refresh();
        }

    }

    @FXML
    void GoToUserTable(MouseEvent event) throws IOException {
        DB.DisconnectFromDataBase();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GraphicUserInterface/AdminOnlyScreenUser.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void Revert(MouseEvent event) throws IOException, SQLException {
        Barang Barangpilihan = TableBarang.getSelectionModel().getSelectedItem();
        if (Barangpilihan.getStatus().equals("Pending")) {
            Barangpilihan.setStatus("Belum Terjual");
            DB.UpdateStatusBarang(Barangpilihan.getDecoyID(), "Belum Terjual");
            Image NewImage = Barangpilihan.getGambar();
            try (PNGWriter writer = new PNGWriter(
                    Files.newOutputStream(Path.of("src/product Untuk Testing Kelola Katalog/ImageHolder.PNG")))) {
                writer.write(PNGJavaFXUtils.asArgbImage(NewImage));
            }
            File NewImageFile = new File("src/product Untuk Testing Kelola Katalog/ImageHolder.PNG");
            DB.InsertBarang(Barangpilihan.getNamaBarang(), Barangpilihan.getHargaBarang(), Barangpilihan.getUser(),
                    Barangpilihan.getKondisi(),
                    Barangpilihan.getUkuranBarang(), Barangpilihan.getBrandBarang(), Barangpilihan.getWarnaBarang(),
                    Barangpilihan.getKategoriBarang(), Barangpilihan.getDeskripsiBarang(), NewImageFile,
                    Barangpilihan.getStatus());
        }
    }

    @FXML
    void Keluar(MouseEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
        System.exit(0);
    }

    void imagetofile(Image image) {

    }

}
