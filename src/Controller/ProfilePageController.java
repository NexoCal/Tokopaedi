package Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import Model.SceneTracker;
import Model.User;
import animatefx.animation.SlideInRight;
import animatefx.animation.ZoomIn;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class ProfilePageController implements Initializable {

    DatabaseModel DB = new DatabaseModel();

    @FXML
    private Button BatalAlamat;

    @FXML
    private Pane CardChange1;

    @FXML
    private TextArea AlamatAdvance;

    @FXML
    private TextField AlamatField;

    @FXML
    private WebView Map;

    @FXML
    private Button UbahAlamatAdvance;

    @FXML
    private ImageView ProfileImage;

    @FXML
    private Label Alamat;

    @FXML
    private Button Batal;

    @FXML
    private Pane CardChange;

    @FXML
    private Label Email;

    @FXML
    private Label JenisKelamin;

    @FXML
    private Label Nama;

    @FXML
    private Label Nomor;

    @FXML
    private Pane Overlay;

    @FXML
    private Pane OverlayBackground;

    @FXML
    private TextField Replacement;

    @FXML
    private Label TanggalLahir;

    @FXML
    private Button Ubah;

    @FXML
    private Button UbahAlamat;

    @FXML
    private Button UbahEmail;

    @FXML
    private Button UbahJenisKelamin;

    @FXML
    private Button UbahNama;

    @FXML
    private Button UbahNomor;

    @FXML
    private Label UbahSubject;

    @FXML
    private Button UbahTanggalLahir;

    @FXML
    private Button UbahUsername;

    @FXML
    private Label Username;

    @FXML
    private ChoiceBox<String> PilihKelamin;

    @FXML
    private DatePicker PilihTanggal;

    @FXML
    private Button PilihGambar;

    @FXML
    private Label DetailID;

    @FXML
    private Label AlamatLandMark;

    @FXML
    private Label NotifGeneral;

    private String Subject;

    private String[] Kelamin = { "Laki - Laki", "Perempuan", "Tidak ingin memberitahu" };

    private WebEngine engine;

    void load() {
        engine.load("https://www.google.com/maps/@-7.7374005,110.3460011,13z?entry=ttu");
    }

    User x = new User();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        DB.ConnectToDataBase("src/TokopaediDatabase.db");
        User DataCurrentUser = DB.SelectUser(x.getID());

        Integer IDint = DataCurrentUser.getID();
        String ID = IDint.toString();

        switch (ID.length()) {
            case 1:
                DetailID.setText("00000" + ID);
                break;
            case 2:
                DetailID.setText("0000" + ID);
                break;
            case 3:
                DetailID.setText("000" + ID);
                break;
            case 4:
                DetailID.setText("00" + ID);
                break;
            case 5:
                DetailID.setText("0" + ID);
                break;
            case 6:
                DetailID.setText(ID);
                break;
        }
        String [] AlamatList;
        if(DataCurrentUser.getAlamat() != null){
        AlamatList = DataCurrentUser.getAlamat().split(" \\| ");
        }else{
            AlamatList = new String[2];
            AlamatList[0] = "Not Set";
            AlamatList[1] = "Not Set";
        }

        Nama.setText(DataCurrentUser.getNama());
        Username.setText(DataCurrentUser.getUsername());
        Nomor.setText(DataCurrentUser.getNomor());
        AlamatLandMark.setText(AlamatList[0]);
        Alamat.setText(AlamatList[1]);
        Email.setText(DataCurrentUser.getEmail());
        JenisKelamin.setText(DataCurrentUser.getJenisKelamin());
        TanggalLahir.setText(DataCurrentUser.getTanggalLahir());
        if (DataCurrentUser.getGambarProfile() != null) {
            ProfileImage.setImage(DataCurrentUser.getGambarProfile());
        }
        PilihKelamin.getItems().addAll(Kelamin);
        engine = Map.getEngine();
        load();

        PilihKelamin.setVisible(false);
        Replacement.setVisible(false);
        PilihTanggal.setVisible(false);

    }

    @FXML
    void UbahAlamatClick(MouseEvent event) {
        Replacement.setVisible(true);
        TranslateTransition Overlaymove = new TranslateTransition(Duration.millis(1), OverlayBackground);
        TranslateTransition Overlaycardmove = new TranslateTransition(Duration.millis(1), CardChange1);

        Overlaymove.setInterpolator(Interpolator.DISCRETE);
        Overlaymove.setToY(800);

        Overlaycardmove.setInterpolator(Interpolator.DISCRETE);
        Overlaycardmove.setToY(750);

        Overlaymove.play();
        Overlaycardmove.play();

        Subject = "Alamat";

    }

    @FXML
    void UbahEmailClick(MouseEvent event) {
        Replacement.setVisible(true);
        TranslateTransition Overlaymove = new TranslateTransition(Duration.millis(1), OverlayBackground);
        TranslateTransition Overlaycardmove = new TranslateTransition(Duration.millis(1), Overlay);

        Overlaymove.setInterpolator(Interpolator.DISCRETE);
        Overlaymove.setToY(800);

        Overlaycardmove.setInterpolator(Interpolator.DISCRETE);
        Overlaycardmove.setToY(500);

        Overlaymove.play();
        Overlaycardmove.play();

        UbahSubject.setText("Email");
        Subject = "Email";

    }

    @FXML
    void UbahKelaminClick(MouseEvent event) {
        PilihKelamin.setVisible(true);
        TranslateTransition Overlaymove = new TranslateTransition(Duration.millis(1), OverlayBackground);
        TranslateTransition Overlaycardmove = new TranslateTransition(Duration.millis(1), Overlay);

        Overlaymove.setInterpolator(Interpolator.DISCRETE);
        Overlaymove.setToY(800);

        Overlaycardmove.setInterpolator(Interpolator.DISCRETE);
        Overlaycardmove.setToY(500);

        Overlaymove.play();
        Overlaycardmove.play();

        UbahSubject.setText("Jenis Kelamin");
        Subject = "JenisKelamin";

    }

    @FXML
    void UbahNamaClick(MouseEvent event) {
        Replacement.setVisible(true);
        TranslateTransition Overlaymove = new TranslateTransition(Duration.millis(1), OverlayBackground);
        TranslateTransition Overlaycardmove = new TranslateTransition(Duration.millis(1), Overlay);

        Overlaymove.setInterpolator(Interpolator.DISCRETE);
        Overlaymove.setToY(800);

        Overlaycardmove.setInterpolator(Interpolator.DISCRETE);
        Overlaycardmove.setToY(500);

        Overlaymove.play();
        Overlaycardmove.play();

        UbahSubject.setText("Nama");
        Subject = "Nama";
    }

    @FXML
    void UbahNomorClick(MouseEvent event) {
        Replacement.setVisible(true);
        TranslateTransition Overlaymove = new TranslateTransition(Duration.millis(1), OverlayBackground);
        TranslateTransition Overlaycardmove = new TranslateTransition(Duration.millis(1), Overlay);

        Overlaymove.setInterpolator(Interpolator.DISCRETE);
        Overlaymove.setToY(800);

        Overlaycardmove.setInterpolator(Interpolator.DISCRETE);
        Overlaycardmove.setToY(500);

        Overlaymove.play();
        Overlaycardmove.play();

        UbahSubject.setText("Nomor");
        Subject = "Nomor";
    }

    @FXML
    void UbahTanggalLahirClick(MouseEvent event) {
        PilihTanggal.setVisible(true);
        TranslateTransition Overlaymove = new TranslateTransition(Duration.millis(1), OverlayBackground);
        TranslateTransition Overlaycardmove = new TranslateTransition(Duration.millis(1), Overlay);

        Overlaymove.setInterpolator(Interpolator.DISCRETE);
        Overlaymove.setToY(800);

        Overlaycardmove.setInterpolator(Interpolator.DISCRETE);
        Overlaycardmove.setToY(500);

        Overlaymove.play();
        Overlaycardmove.play();

        UbahSubject.setText("Tanggal Lahir");
        Subject = "TanggalLahir";

    }

    @FXML
    void UbahUsernameClick(MouseEvent event) {
        Replacement.setVisible(true);
        TranslateTransition Overlaymove = new TranslateTransition(Duration.millis(1), OverlayBackground);
        TranslateTransition Overlaycardmove = new TranslateTransition(Duration.millis(1), Overlay);

        Overlaymove.setInterpolator(Interpolator.DISCRETE);
        Overlaymove.setToY(800);

        Overlaycardmove.setInterpolator(Interpolator.DISCRETE);
        Overlaycardmove.setToY(500);

        Overlaymove.play();
        Overlaycardmove.play();

        UbahSubject.setText("Username");
        Subject = "Username";

    }

    @FXML
    void BatalClick(MouseEvent event) {
        Replacement.setVisible(false);
        PilihKelamin.setVisible(false);
        PilihTanggal.setVisible(false);
        TranslateTransition Overlaymove = new TranslateTransition(Duration.millis(1), OverlayBackground);
        TranslateTransition Overlaycardmove = new TranslateTransition(Duration.millis(1), Overlay);

        Overlaymove.setInterpolator(Interpolator.DISCRETE);
        Overlaymove.setToY(0);

        Overlaycardmove.setInterpolator(Interpolator.DISCRETE);
        Overlaycardmove.setToY(0);

        Overlaycardmove.play();
        Overlaymove.play();

        NotifGeneral.setVisible(false);
    }

    @FXML
    void UbahClick(MouseEvent event) {
        String NewData = Replacement.getText();
        String NewDataKelamin = PilihKelamin.getValue();
        LocalDate Tanggal = PilihTanggal.getValue();
        if (!NewData.equals("") || NewDataKelamin != null || Tanggal != null) {
            if (Subject.equals("Alamat")) {
                Alamat.setText(NewData);
                DB.Update(x.getID(), Subject, NewData);
                closeUbahPopUp();
            } else if (Subject.equals("Username")) {
                Username.setText(NewData);
                DB.Update(x.getID(), Subject, NewData);
                closeUbahPopUp();
            } else if (Subject.equals("Nama")) {
                Nama.setText(NewData);
                DB.Update(x.getID(), Subject, NewData);
                closeUbahPopUp();
            } else if (Subject.equals("Nomor")) {
                if (NewData.length() >= 12) {
                    Nomor.setText(NewData);
                    DB.Update(x.getID(), Subject, NewData);
                    closeUbahPopUp();
                } else {
                    NotifGeneral.setVisible(true);
                    NotifGeneral.setText("Nomor harus 12+ digit");
                    FadeTransition shownotif = new FadeTransition(Duration.millis(1), NotifGeneral);
                    shownotif.setFromValue(0.0);
                    shownotif.setToValue(1.0);
                    shownotif.setOnFinished(e -> {
                        FadeTransition hidenotif = new FadeTransition(Duration.millis(1000), NotifGeneral);
                        hidenotif.setFromValue(1.0);
                        hidenotif.setToValue(0.0);
                        hidenotif.setDelay(Duration.millis(2000));
                        hidenotif.play();
                    });
                    shownotif.play();
                }
            } else if (Subject.equals("Email")) {
                if(NewData.contains("@")){
                Email.setText(NewData);
                DB.Update(x.getID(), Subject, NewData);
                closeUbahPopUp();
                }else{
                    NotifGeneral.setVisible(true);
                    NotifGeneral.setText("Email tidak valid!");
                    FadeTransition shownotif = new FadeTransition(Duration.millis(1), NotifGeneral);
                    shownotif.setFromValue(0.0);
                    shownotif.setToValue(1.0);
                    shownotif.setOnFinished(e -> {
                        FadeTransition hidenotif = new FadeTransition(Duration.millis(1000), NotifGeneral);
                        hidenotif.setFromValue(1.0);
                        hidenotif.setToValue(0.0);
                        hidenotif.setDelay(Duration.millis(2000));
                        hidenotif.play();
                    });
                    shownotif.play();

                }
            } else if (Subject.equals("JenisKelamin")) {
                JenisKelamin.setText(NewDataKelamin);
                DB.Update(x.getID(), Subject, NewDataKelamin);
                closeUbahPopUp();
            } else if (Subject.equals("TanggalLahir")) {
                
                String DateFormat = Tanggal.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
                TanggalLahir.setText(DateFormat);
                DB.Update(x.getID(), Subject, DateFormat);
                closeUbahPopUp();
            }
        } else {
            NotifGeneral.setText("Form kosong! harap diisi terlebih dahulu");
            NotifGeneral.setVisible(true);
            FadeTransition shownotif = new FadeTransition(Duration.millis(1), NotifGeneral);
            shownotif.setFromValue(0.0);
            shownotif.setToValue(1.0);
            shownotif.setOnFinished(e -> {
                FadeTransition hidenotif = new FadeTransition(Duration.millis(1000), NotifGeneral);
                hidenotif.setFromValue(1.0);
                hidenotif.setToValue(0.0);
                hidenotif.setDelay(Duration.millis(2000));
                hidenotif.play();
            });
            shownotif.play();
        }

    }

    void closeUbahPopUp() {
        Replacement.clear();
        PilihKelamin.setValue(null);

        TranslateTransition Overlaymove = new TranslateTransition(Duration.millis(1), OverlayBackground);
        TranslateTransition Overlaycardmove = new TranslateTransition(Duration.millis(1), Overlay);

        Overlaymove.setInterpolator(Interpolator.DISCRETE);
        Overlaymove.setToY(0);

        Overlaycardmove.setInterpolator(Interpolator.DISCRETE);
        Overlaycardmove.setToY(0);

        Overlaycardmove.play();
        Overlaymove.play();

        Replacement.setVisible(false);
        PilihKelamin.setVisible(false);
        PilihTanggal.setVisible(false);
    }

    @FXML
    void UbahAlamatClickAdv(MouseEvent event) {
        String AlamatbaruDetail = AlamatAdvance.getText();
        String AlamatLandmarkbaru = AlamatField.getText();
        String Alamatbaru = AlamatLandmarkbaru + " | " + AlamatbaruDetail;
        Alamat.setText(AlamatbaruDetail);
        AlamatLandMark.setText(AlamatLandmarkbaru);
        DB.Update(x.getID(), Subject, Alamatbaru);

        AlamatField.clear();
        AlamatAdvance.clear();

        TranslateTransition Overlaymove = new TranslateTransition(Duration.millis(1), OverlayBackground);
        TranslateTransition Overlaycardmove = new TranslateTransition(Duration.millis(1), CardChange1);

        Overlaymove.setInterpolator(Interpolator.DISCRETE);
        Overlaymove.setToY(0);

        Overlaycardmove.setInterpolator(Interpolator.DISCRETE);
        Overlaycardmove.setToY(0);

        Overlaycardmove.play();
        Overlaymove.play();

        Replacement.setVisible(false);
        PilihKelamin.setVisible(false);
        PilihTanggal.setVisible(false);

    }

    @FXML
    void BatalAlamatClickAdv(MouseEvent event) {

        TranslateTransition Overlaymove = new TranslateTransition(Duration.millis(1), OverlayBackground);
        TranslateTransition Overlaycardmove = new TranslateTransition(Duration.millis(1), CardChange1);

        Overlaymove.setInterpolator(Interpolator.DISCRETE);
        Overlaymove.setToY(0);

        Overlaycardmove.setInterpolator(Interpolator.DISCRETE);
        Overlaycardmove.setToY(0);

        Overlaycardmove.play();
        Overlaymove.play();

        Replacement.setVisible(false);
        PilihKelamin.setVisible(false);
        PilihTanggal.setVisible(false);

    }

    @FXML
    void PilihGambarFile(MouseEvent event) throws SQLException {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(
                new ExtensionFilter("Image Files (.png, .jpg)", "*.png", "*.jpg"));

        File imageFile = chooser.showOpenDialog(null);
        if (imageFile != null) {
            Image newprofileImage = new Image(imageFile.toPath().toString());
            ProfileImage.setImage(newprofileImage);
            DB.UploadImageProfile(x.getID(), imageFile);

        }
    }

    @FXML
    void Back(MouseEvent event) throws IOException {
        SceneTracker track = new SceneTracker();

        if (track.isFromMainMenu()) {

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

        } else {
            Parent root = FXMLLoader.load(getClass().getResource("/GraphicUserInterface/KatalogPage.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.setResizable(false);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("Tokopaedi");
            stage.show();

            new SlideInRight(root).setSpeed(2.2).play();

            ((Parent) event.getSource()).getScene().getWindow().hide();
        }
    }

}
