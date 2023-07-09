package Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import Model.User;
import animatefx.animation.ZoomIn;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class ProfilePageController implements Initializable {

    DatabaseModel DB = new DatabaseModel();

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

    private String Subject;

    private String [] Kelamin = {"Laki - Laki", "Perempuan", "Tidak ingin memberitahu"};

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
        
        Nama.setText(DataCurrentUser.getNama());
        Username.setText(DataCurrentUser.getUsername());
        Nomor.setText(DataCurrentUser.getNomor());
        Alamat.setText(DataCurrentUser.getAlamat());
        Email.setText(DataCurrentUser.getEmail());
        JenisKelamin.setText(DataCurrentUser.getJenisKelamin());
        TanggalLahir.setText(DataCurrentUser.getTanggalLahir());
        if(DataCurrentUser.getGambarProfile() != null){
            ProfileImage.setImage(DataCurrentUser.getGambarProfile());
        }
        PilihKelamin.getItems().addAll(Kelamin);

        PilihKelamin.setVisible(false);
        Replacement.setVisible(false);
        PilihTanggal.setVisible(false);

    }

    @FXML
    void UbahAlamatClick(MouseEvent event){
        Replacement.setVisible(true);
        TranslateTransition Overlaymove = new TranslateTransition(Duration.millis(1), OverlayBackground);
        TranslateTransition Overlaycardmove = new TranslateTransition(Duration.millis(1), Overlay);

        Overlaymove.setInterpolator(Interpolator.DISCRETE);
        Overlaymove.setToY(800);

        Overlaycardmove.setInterpolator(Interpolator.DISCRETE);
        Overlaycardmove.setToY(500);

        Overlaymove.play();
        Overlaycardmove.play();

        UbahSubject.setText("Alamat");
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
    }

    @FXML
    void UbahClick(MouseEvent event) {
        String NewData = Replacement.getText();
        String NewDataKelamin = PilihKelamin.getValue();
        if (Subject.equals("Alamat")){
            Alamat.setText(NewData);
            DB.Update(x.getID(),Subject, NewData);
        }else if (Subject.equals("Username")){
            Username.setText(NewData);
            DB.Update(x.getID(), Subject, NewData);
        }else if (Subject.equals("Nama")){
            Nama.setText(NewData);
            DB.Update(x.getID(), Subject, NewData);
        }else if (Subject.equals("Nomor")){
            Nomor.setText(NewData);
            DB.Update(x.getID(), Subject, NewData);
        }else if (Subject.equals("Email")){
            Email.setText(NewData);
            DB.Update(x.getID(), Subject, NewData);
        }else if (Subject.equals("JenisKelamin")){
            JenisKelamin.setText(NewDataKelamin);
            DB.Update(x.getID(), Subject, NewDataKelamin);
        }else if (Subject.equals("TanggalLahir")){
            LocalDate Tanggal = PilihTanggal.getValue();
            String DateFormat = Tanggal.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
            TanggalLahir.setText(DateFormat);
            DB.Update(x.getID(), Subject, DateFormat);
        }

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
    void PilihGambarFile(MouseEvent event) throws SQLException {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(
            new ExtensionFilter("Image Files (.png, .jpg)", "*.png", "*.jpg")
        );

        File imageFile = chooser.showOpenDialog(null);
        if (imageFile != null){
            Image newprofileImage = new Image(imageFile.toPath().toString());
            ProfileImage.setImage(newprofileImage);
            DB.UploadImageProfile(x.getID(),imageFile);
            
        }
    }

    @FXML
    void Back(MouseEvent event) throws IOException {
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
