package Controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import Model.Session;
import Model.User;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class ProfilePageController implements Initializable {

    DatabaseModel DB = new DatabaseModel();

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

    private String Subject;

    private String [] Kelamin = {"Laki - Laki", "Perempuan", "Tidak ingin memberitahu"};

    Session x = new Session();
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        DB.ConnectToDataBase("src/TokopaediDatabase.db");
        User DataCurrentUser = DB.SelectUser(x.getID());

        Nama.setText(DataCurrentUser.getNama());
        Username.setText(DataCurrentUser.getUsername());
        Nomor.setText(DataCurrentUser.getNomor());
        Alamat.setText(DataCurrentUser.getAlamat());
        Email.setText(DataCurrentUser.getEmail());
        JenisKelamin.setText(DataCurrentUser.getJenisKelamin());
        TanggalLahir.setText(DataCurrentUser.getTanggalLahir());
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
        LocalDate Tanggal = PilihTanggal.getValue();
        String DateFormat = Tanggal.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
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

}
