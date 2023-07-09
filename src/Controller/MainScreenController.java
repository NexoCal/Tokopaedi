package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Model.Barang;
import Model.SceneTracker;
import Model.SearchListener;
import Model.User;
import animatefx.animation.SlideInDown;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainScreenController implements Initializable {
    DatabaseModel DB = new DatabaseModel();
    User Session = new User();
    Boolean MenuOn = false;

    private List<Barang> allproduct;
    private List<Integer> IDs;

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
        SceneTracker track = new SceneTracker();
        track.setTrack(true);
        allproduct = allproduk();
        IDs = GetIDs();
        User CurrentUser = DB.SelectUser(Session.getID());
        NamaUser.setText(CurrentUser.getNama());

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
            if (allproduct.size() < 10){
                recentTabCardController.SetHBox(IDs);
            }else{
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

    }

    private List<Barang> allproduk() {
        DB.ConnectToDataBase("src/TokopaediDatabase.db");
        List<Barang> temp = DB.DaftarBarangLengkap();
        return temp;
    }

    private List<Integer> GetIDs() {
        DB.ConnectToDataBase("src/TokopaediDatabase.db");
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
        SearchListener temp = new SearchListener();
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

}
