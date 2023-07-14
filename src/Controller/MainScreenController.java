package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Model.Barang;
import Model.SceneTracker;
import Model.SearchListener;
import Model.User;
import animatefx.animation.FadeIn;
import animatefx.animation.SlideInDown;
import animatefx.animation.SlideInLeft;
import animatefx.animation.SlideOutLeft;
import animatefx.animation.SlideOutRight;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class MainScreenController implements Initializable {
    DatabaseModel DB = new DatabaseModel();
    User Session = new User();
    Boolean MenuOn = false;
    Boolean SideOn = false;

    private List<Barang> allproduct;
    private List<Integer> IDs;

    @FXML
    private Pane SidePanel;

    @FXML
    private Pane overlayer;

    @FXML
    private HBox Userdetail;

    @FXML
    private VBox VboxCardContainer;

    @FXML
    private Label NamaUser;

    @FXML
    private Pane UserMenu;

    @FXML
    private Pane Search;

    @FXML
    private TextField SearchBar;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        DB.ConnectToDataBase("src/TokopaediDatabase.db");
        SceneTracker track = new SceneTracker();
        track.setTrack(true);
        allproduct = allproduk();
        IDs = GetIDs();
        User CurrentUser = DB.SelectUser(Session.getID());
        NamaUser.setText(CurrentUser.getNama());
        SidePanel.setVisible(false);

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/GraphicUserInterface/KatagoriTab.fxml"));
            VBox Base = loader.load();
            KategoriCardTabController kategoriCardTabController = loader.getController();
            kategoriCardTabController.SetHBox();
            VboxCardContainer.getChildren().add(Base);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/GraphicUserInterface/RecentTabCard.fxml"));
            VBox Base = loader.load();
            RecentTabCardController recentTabCardController = loader.getController();
            if (allproduct.size() < 10) {
                recentTabCardController.SetHBox(IDs);
            } else {
                recentTabCardController.SetHBoxbyBarang(DB.DaftarBarangDisplayRecent());
            }
            VboxCardContainer.getChildren().add(Base);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/GraphicUserInterface/RecommendedTabCard.fxml"));
            VBox Base = loader.load();
            RecommendedTabCardController recommendedTabCardController = loader.getController();
            recommendedTabCardController.setGridbyDaftarBarang(DB.DaftarBarangDisplay());
            VboxCardContainer.getChildren().add(Base);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        DB.DisconnectFromDataBase();

    }

    private List<Barang> allproduk() {
        List<Barang> temp = DB.DaftarBarangLengkap();
        return temp;
    }

    private List<Integer> GetIDs() {
        List<Integer> IDs = DB.SelectAllID();
        return IDs;
    }

    @FXML
    void MenuTrigger(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GraphicUserInterface/UserMenu.fxml"));
        Pane Menu = loader.load();
        if (MenuOn) {
            UserMenu.getChildren().clear();
            MenuOn = false;
        } else {
            UserMenu.getChildren().add(Menu);
            MenuOn = true;
        }

    }

    @FXML
    void EnterSearch(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            SearchListener temp = new SearchListener();
            String Searched = SearchBar.getText();
            temp.setSearch(Searched);
            temp.setIskategoriSearch(false);

            DB.DisconnectFromDataBase();

            Parent root = FXMLLoader.load(getClass().getResource("/GraphicUserInterface/SearchResultPage.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.setResizable(false);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("Tokopaedi");
            stage.show();

            new SlideInDown(root).setSpeed(2.5).play();

            ((Parent) event.getSource()).getScene().getWindow().hide();
        }
    }

    @FXML
    void Search(MouseEvent event) throws IOException {
        DB.DisconnectFromDataBase();
        SearchListener temp = new SearchListener();
        temp.setIskategoriSearch(false);
        String Searched = SearchBar.getText();
        temp.setSearch(Searched);

        Parent root = FXMLLoader.load(getClass().getResource("/GraphicUserInterface/SearchResultPage.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Tokopaedi");
        stage.show();

        new SlideInDown(root).setSpeed(2.5).play();

        ((Parent) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    void ShowSidePanel(MouseEvent event) throws IOException {

        if (SideOn) {
            SidePanel.setVisible(false);
            FadeTransition x = new FadeTransition(Duration.millis(500), overlayer);
            x.setFromValue(0.5);
            x.setToValue(0.0);
            SlideOutLeft y = new SlideOutLeft();
            y.setNode(SidePanel);
            y.setSpeed(2.0);
            y.setOnFinished(f -> {
                SidePanel.getChildren().clear();
            });
            x.setOnFinished(e -> {TranslateTransition overlay = new TranslateTransition(Duration.millis(1), (overlayer));
            overlay.setToX(0);overlay.play();});
            y.play();
            x.play();
            SideOn = false;
        } else {
            TranslateTransition overlay = new TranslateTransition(Duration.millis(1), (overlayer));
            overlay.setToX(1280);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/GraphicUserInterface/SidePaneSetter.fxml"));
            ScrollPane SidePane = loader.load();
            SidePanel.getChildren().add(SidePane);
            
            overlay.setOnFinished(e -> {
                FadeTransition x = new FadeTransition(Duration.millis(500), overlayer);
                x.setFromValue(0.0);
                x.setToValue(0.5);
                x.play();
                SidePanel.setVisible(true);
            });
            overlay.play();
            new SlideInLeft(SidePanel).setSpeed(2.0).play();
            SideOn = true;
        }

    }

}
