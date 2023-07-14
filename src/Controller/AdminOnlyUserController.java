package Controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Model.Barang;
import Model.User;
import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AdminOnlyUserController implements Initializable {

    DatabaseModel DB = new DatabaseModel();

    @FXML
    private TextArea AlamatField;

    @FXML
    private TextField IDSearchField;

    @FXML
    private TextField NamaField;

    @FXML
    private TextField SandiField;

    @FXML
    private TableColumn<User, String> AlamatCol;

    @FXML
    private TableColumn<User, String> EmailCol;

    @FXML
    private TableColumn<User, String> IDCol;

    @FXML
    private TableColumn<User, String> NamaCol;

    @FXML
    private TableColumn<User, String> NomorCol;

    @FXML
    private TableColumn<User, String> SandiCol;

    @FXML
    private TableView<User> TableUser;

    @FXML
    private Label Warning;

    @FXML
    private Label BarangNav;

    @FXML
    private Label KeluarNav;

    @FXML
    private Label UserNav;

    ObservableList<User> DataUser = FXCollections.observableArrayList();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        DB.ConnectToDataBase("src/TokopaediDatabase.db");
        IDCol.setCellValueFactory(new PropertyValueFactory<User, String>("DecoyID"));
        NamaCol.setCellValueFactory(new PropertyValueFactory<User, String>("Nama"));
        SandiCol.setCellValueFactory(new PropertyValueFactory<User, String>("Sandi"));
        NomorCol.setCellValueFactory(new PropertyValueFactory<User, String>("Nomor"));
        EmailCol.setCellValueFactory(new PropertyValueFactory<User, String>("Email"));
        AlamatCol.setCellValueFactory(new PropertyValueFactory<User, String>("Alamat"));
        Warning.setOpacity(0.0);

        List<User> SemuaUser = DB.DaftarUserLengkap();

        for (User i : SemuaUser) {
            DataUser.add(i);
        }

        TableUser.setItems(DataUser);

    }

    private void Screenrefresh() {
        List<User> SemuaUser = DB.DaftarUserLengkap();
        ObservableList<User> NewDataUser = FXCollections.observableArrayList();
        for (User i : SemuaUser) {
            NewDataUser.add(i);
        }

        TableUser.setItems(NewDataUser);
    }

    private void FadeAnimation(Node node) {
        FadeIn AnimationIn = new FadeIn(node);
        AnimationIn.setSpeed(1.5);
        AnimationIn.setOnFinished(e -> {
            FadeOut AnimationOut = new FadeOut(node);
            AnimationOut.setDelay(Duration.millis(1000));
            AnimationOut.setSpeed(1.5);
            AnimationOut.play();

        });
        AnimationIn.play();
    }

    @FXML
    void DeleteSelected(MouseEvent event) {

        User Userpilihan = TableUser.getSelectionModel().getSelectedItem();
        if (Userpilihan != null) {
            DB.DeleteUser(Userpilihan.getDecoyID());

            TableUser.getItems().removeAll(TableUser.getSelectionModel().getSelectedItem());
        } else {
            Warning.setText("User yang dipilihan tidak ditemukan");
            FadeAnimation(Warning);
        }
    }

    @FXML
    void SearchID(MouseEvent event) {
        ObservableList<User> SearchedDataUser = FXCollections.observableArrayList();
        String ID = IDSearchField.getText();
        try {
            Integer.valueOf(ID);
        } catch (Exception e) {
            ID = "";
            Warning.setText("Masukan angka sebagai ID");
            FadeAnimation(Warning);
        }
        if (!ID.equals("")) {
            for (User i : DataUser) {
                if (i.getDecoyID() == Integer.valueOf(ID)) {
                    SearchedDataUser.add(i);
                }
            }
            TableUser.setItems(SearchedDataUser);
        } else {
            TableUser.setItems(DataUser);
        }
    }

    @FXML
    void UbahData(MouseEvent event) throws NumberFormatException, FileNotFoundException, IOException {
        String NamaBaru = NamaField.getText();
        String SandiBaru = SandiField.getText();
        String ID = IDSearchField.getText();

        if (NamaBaru.equals("") && SandiBaru.equals("") || ID.equals("")) {
            if (ID.equals("")) {
                Warning.setText("ID Target tidak ditemukan");
                FadeAnimation(Warning);
            } else {
                Warning.setText("Nama dan Sandi kosong");
                FadeAnimation(Warning);
            }
        } else if (!NamaBaru.equals("") && SandiBaru.equals("")) {
            DB.UpdateUserNama(Integer.valueOf(ID), NamaBaru);
            Screenrefresh();
        } else if (NamaBaru.equals("") && !SandiBaru.equals("")) {
            if (SandiBaru.length() < 8) {
                Warning.setText("Sandi kurang dari 8 digit");
                FadeAnimation(Warning);
            } else {
                DB.UpdateUserSandi(Integer.valueOf(ID), SandiBaru);
                Screenrefresh();
            }
        } else {
            if (SandiBaru.length() < 8) {
                Warning.setText("Sandi kurang dari 8 digit");
                FadeAnimation(Warning);
            } else {
                DB.UpdateUserSandi(Integer.valueOf(ID), SandiBaru);
                DB.UpdateUserNama(Integer.valueOf(ID), NamaBaru);
                Screenrefresh();
            }

        }
    }

    @FXML
    void GetData(MouseEvent event) {
        User Userpilihan = TableUser.getSelectionModel().getSelectedItem();
        if (Userpilihan != null) {
            NamaField.setText(Userpilihan.getNama());
            SandiField.setText(Userpilihan.getSandi());
            IDSearchField.setText(Integer.toString(Userpilihan.getDecoyID()));
            if (Userpilihan.getAlamat() != null) {
                AlamatField.clear();
                AlamatField.appendText(Userpilihan.getAlamat());
            } else {
                AlamatField.clear();
            }
        }

    }

    @FXML
    void GetDataKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            User Userpilihan = TableUser.getSelectionModel().getSelectedItem();
            NamaField.setText(Userpilihan.getNama());
            SandiField.setText(Userpilihan.getSandi());
            if (Userpilihan.getAlamat() != null) {
                AlamatField.clear();
                AlamatField.appendText(Userpilihan.getAlamat());
            } else {
                AlamatField.clear();
            }
        }

    }

    @FXML
    void GoToBarangTable(MouseEvent event) throws IOException {
        DB.DisconnectFromDataBase();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GraphicUserInterface/AdminOnlyScreenBarang.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void Keluar(MouseEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
        System.exit(0);
    }

}
