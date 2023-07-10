
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Model.User;
import javafx.util.Duration;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginScreenController implements Initializable {

    // ===============[FX Component]===============//
    @FXML
    private Pane BackCard;

    @FXML
    private Pane BackgroundBlack;

    @FXML
    private Button DaftarButton;

    @FXML
    private Label DaftarDetailKonfirmasiField;

    @FXML
    private Label DaftarDetailNamaField;

    @FXML
    private Label DaftarDetailNomorField;

    @FXML
    private Label DaftarDetailSandiField;

    @FXML
    private PasswordField DaftarKonfirmasiField;

    @FXML
    private Label DaftarLabel;

    @FXML
    private TextField DaftarNamaField;

    @FXML
    private TextField DaftarNomorField;

    @FXML
    private PasswordField DaftarSandiField;

    @FXML
    private Button DaftarSwitch;

    @FXML
    private Pane GroupDaftar;

    @FXML
    private Pane GroupMasuk;

    @FXML
    private ImageView ImageDaftar;

    @FXML
    private ImageView ImageMasuk;

    @FXML
    private Button MasukButton;

    @FXML
    private Label MasukDetailNamaField;

    @FXML
    private Label MasukDetailSandiField;

    @FXML
    private Label MasukLabel;

    @FXML
    private TextField MasukNamaField;

    @FXML
    private PasswordField MasukSandiField;

    @FXML
    private Button MasukSwitch;

    @FXML
    private Label Notifier;

    @FXML
    private Pane SlideCard;

    @FXML
    private Label TitleTokoPaedi;

    @FXML
    private ImageView TopoWhite;

    @FXML
    private Label TitleTokoPaediGlow;

    // ===============[Initializer]===============//

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        GroupDaftar.setVisible(false);
        ImageDaftar.setVisible(false);
        DaftarLabel.setVisible(false);
        TopoWhite.setVisible(false);
    }

    // ===============[Custom Interpolator]===============//

    // Section ini dipergunakan untuk movement dari animasi

    Interpolator Custom1 = new Interpolator() {
        @Override
        protected double curve(double e) {
            return (e == 1.0) ? 1.0 : 1 - Math.pow(2.0, -10 * e);
        }
    };

    // ===============[Code]===============//

    @FXML
    void SwitchToDaftar(MouseEvent event) {
        TranslateTransition Slide = new TranslateTransition();
        TranslateTransition BackgroundSwitch = new TranslateTransition(Duration.millis(1000), BackgroundBlack);
        FadeTransition HideDaftar = new FadeTransition(Duration.millis(500), DaftarSwitch);
        FadeTransition ShowMasuk = new FadeTransition(Duration.millis(1500), MasukSwitch);
        FadeTransition Daftar = new FadeTransition(Duration.millis(500), GroupDaftar);
        FadeTransition Masuk = new FadeTransition(Duration.millis(250), GroupMasuk);
        FadeTransition GambarMasuk = new FadeTransition(Duration.millis(500), ImageMasuk);
        FadeTransition LabelMasuk = new FadeTransition(Duration.millis(500), MasukLabel);
        FadeTransition GambarDaftar = new FadeTransition(Duration.millis(1100), ImageDaftar);
        FadeTransition LabelDaftar = new FadeTransition(Duration.millis(1300), DaftarLabel);
        FadeTransition Topo = new FadeTransition(Duration.millis(300), TopoWhite);

        GroupDaftar.setVisible(true);
        ImageDaftar.setVisible(true);
        DaftarLabel.setVisible(true);
        TopoWhite.setVisible(true);

        Slide.setDuration(Duration.millis(1000));
        Slide.setNode(SlideCard);
        Slide.setInterpolator(Custom1);
        Slide.setToX(421);

        BackgroundSwitch.setInterpolator(Custom1);
        BackgroundSwitch.setToX(1280);

        Daftar.setFromValue(0.0);
        Daftar.setToValue(1.0);
        Masuk.setFromValue(1.0);
        Masuk.setToValue(0.0);

        LabelDaftar.setFromValue(0.0);
        LabelDaftar.setToValue(1.0);
        GambarDaftar.setFromValue(0.0);
        GambarDaftar.setToValue(1.0);

        Topo.setFromValue(0.0);
        Topo.setToValue(1.0);

        LabelMasuk.setFromValue(1.0);
        LabelMasuk.setToValue(0.0);
        GambarMasuk.setFromValue(1.0);
        GambarMasuk.setToValue(0.0);

        HideDaftar.setFromValue(1.0);
        ShowMasuk.setFromValue(0.0);
        HideDaftar.setToValue(0.0);
        ShowMasuk.setToValue(1.0);

        Slide.play();
        BackgroundSwitch.play();
        Topo.play();
        Daftar.play();
        Masuk.play();
        LabelDaftar.play();
        GambarDaftar.play();
        LabelMasuk.play();
        GambarMasuk.play();
        ShowMasuk.play();
        HideDaftar.play();

        Notifier.setText("");

    }

    @FXML
    void SwitchToMasuk(MouseEvent event) {

        TranslateTransition Slide = new TranslateTransition();
        TranslateTransition BackgroundSwitch = new TranslateTransition(Duration.millis(1000), BackgroundBlack);
        TranslateTransition Reset = new TranslateTransition(Duration.millis(1), BackgroundBlack);
        FadeTransition HideDaftar = new FadeTransition(Duration.millis(1500), DaftarSwitch);
        FadeTransition ShowMasuk = new FadeTransition(Duration.millis(500), MasukSwitch);
        FadeTransition Daftar = new FadeTransition(Duration.millis(250), GroupDaftar);
        FadeTransition Masuk = new FadeTransition(Duration.millis(500), GroupMasuk);
        FadeTransition GambarMasuk = new FadeTransition(Duration.millis(1100), ImageMasuk);
        FadeTransition LabelMasuk = new FadeTransition(Duration.millis(1300), MasukLabel);
        FadeTransition GambarDaftar = new FadeTransition(Duration.millis(500), ImageDaftar);
        FadeTransition LabelDaftar = new FadeTransition(Duration.millis(500), DaftarLabel);
        FadeTransition Topo = new FadeTransition(Duration.millis(300), TopoWhite);

        Slide.setDuration(Duration.millis(1000));
        Slide.setNode(SlideCard);
        Slide.setInterpolator(Custom1);
        Slide.setToX(0);

        BackgroundSwitch.setInterpolator(Custom1);
        BackgroundSwitch.setToX(2560);

        Reset.setDelay(Duration.millis(1000));
        Reset.setInterpolator(Interpolator.DISCRETE);
        Reset.setToX(0);

        Daftar.setFromValue(1.0);
        Daftar.setToValue(0.0);
        Masuk.setFromValue(0.0);
        Masuk.setToValue(1.0);

        LabelDaftar.setFromValue(1.0);
        LabelDaftar.setToValue(0.0);
        GambarDaftar.setFromValue(1.0);
        GambarDaftar.setToValue(0.0);

        Topo.setFromValue(1.0);
        Topo.setToValue(0.0);

        LabelMasuk.setFromValue(0.0);
        LabelMasuk.setToValue(1.0);
        GambarMasuk.setFromValue(0.0);
        GambarMasuk.setToValue(1.0);

        HideDaftar.setFromValue(0.0);
        ShowMasuk.setFromValue(1.0);
        HideDaftar.setToValue(1.0);
        ShowMasuk.setToValue(0.0);

        Slide.play();
        BackgroundSwitch.play();
        Topo.play();
        Reset.play();
        Daftar.play();
        Masuk.play();
        LabelDaftar.play();
        GambarDaftar.play();
        LabelMasuk.play();
        GambarMasuk.play();
        ShowMasuk.play();
        HideDaftar.play();

        BackgroundSwitch.setDuration(Duration.millis(1));
        BackgroundSwitch.setInterpolator(Custom1);
        BackgroundSwitch.setToX(0);

        Notifier.setText("");
        DaftarNamaField.clear();
        DaftarNomorField.clear();
        DaftarSandiField.clear();
        DaftarKonfirmasiField.clear();

        GroupDaftar.setVisible(false);
    }

    @FXML
    void DaftarUser(MouseEvent event) {
        DatabaseModel Database = new DatabaseModel();

        String Nama = DaftarNamaField.getText();
        String Nomor = DaftarNomorField.getText();
        String Sandi = DaftarSandiField.getText();
        String Konfirmasi = DaftarKonfirmasiField.getText();

        PauseTransition pause = new PauseTransition(Duration.millis(3000));
        FadeTransition HideNotifier = new FadeTransition(Duration.millis(800), Notifier);

        if (Nama.isBlank()) {
                Notifier.setText("Nama Harus Diisi");
                SequentialTransition play = new SequentialTransition(pause, HideNotifier);
                HideNotifier.setFromValue(1.0);
                HideNotifier.setToValue(0.0);
                play.play();
        } else {
            if (Nomor.length() < 12) {
                Notifier.setText("Nomor harus 12+ digit");
                SequentialTransition play = new SequentialTransition(pause, HideNotifier);
                HideNotifier.setFromValue(1.0);
                HideNotifier.setToValue(0.0);
                play.play();
            } else if (Sandi.length() <= 7) {
                Notifier.setText("Sandi harus 8+ digit");
                SequentialTransition play = new SequentialTransition(pause, HideNotifier);
                HideNotifier.setFromValue(1.0);
                HideNotifier.setToValue(0.0);
                play.play();
            } else {

                if (Konfirmasi.equals(Sandi)) {
                    Database.ConnectToDataBase("src/TokopaediDatabase.db");
                    Database.InsertUser(Nama, Nomor, Sandi);
                    DaftarNamaField.clear();
                    DaftarNomorField.clear();
                    DaftarSandiField.clear();
                    DaftarKonfirmasiField.clear();
                    Database.DisconnectFromDataBase();
                    SwitchToMasuk(event);
                } else {
                    Notifier.setText("Sandi tidak sama");
                    SequentialTransition play = new SequentialTransition(pause, HideNotifier);
                    HideNotifier.setFromValue(1.0);
                    HideNotifier.setToValue(0.0);
                    play.play();
                }
            }
        }

    }

    @FXML
    void MasukUser(MouseEvent event) throws IOException {
        DatabaseModel Database = new DatabaseModel();
        Database.ConnectToDataBase("src/TokopaediDatabase.db");

        String Nama = MasukNamaField.getText();
        String Sandi = MasukSandiField.getText();

        PauseTransition pause = new PauseTransition(Duration.millis(3000));
        FadeTransition HideNotifier = new FadeTransition(Duration.millis(800), Notifier);

        if (Nama.length() == 0 && Sandi.length() == 0) {
            Notifier.setText("Form Kosong");
            SequentialTransition play = new SequentialTransition(pause, HideNotifier);
            HideNotifier.setFromValue(1.0);
            HideNotifier.setToValue(0.0);
            play.play();
            Database.DisconnectFromDataBase();

        } else if (Database.AuthenticationBySandiDanNama(Sandi, Nama)) {
            User x = new User();
            x.setID(Database.getID(Sandi, Nama));
            Database.DisconnectFromDataBase();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GraphicUserInterface/MainScreen.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) BackCard.getScene().getWindow();

            stage.setScene(scene);
            stage.setResizable(false);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("Tokopaedi");
            stage.show();

        } else {
            Notifier.setText("Nama atau Sandi Salah");
            SequentialTransition play = new SequentialTransition(pause, HideNotifier);
            HideNotifier.setFromValue(1.0);
            HideNotifier.setToValue(0.0);
            play.play();
            Database.DisconnectFromDataBase();
        }
    }

}
