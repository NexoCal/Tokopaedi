package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Model.Barang;
import Model.SearchListener;
import Model.User;
import animatefx.animation.SlideInDown;
import animatefx.animation.SlideInLeft;
import animatefx.animation.SlideOutLeft;
import animatefx.animation.ZoomIn;
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

public class SearchPageController implements Initializable {
    DatabaseModel DB = new DatabaseModel();
    SearchListener searched = new SearchListener();
    boolean MenuOn = false;
    boolean SideOn = false;

    @FXML
    private Label NamaUser;

    @FXML
    private Pane NotifkasiNoResult;

    @FXML
    private Pane Search;

    @FXML
    private TextField SearchBar;

    @FXML
    private HBox Userdetail;

    @FXML
    private VBox VboxConatiner;

    @FXML
    private Pane UserMenu;

    @FXML
    private Pane SidePanel;

    @FXML
    private Pane overlayer;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        DB.ConnectToDataBase("src/TokopaediDatabase.db");
        User tempx = new User();
        User CurrentUser = DB.SelectUser(tempx.getID());
        NamaUser.setText(CurrentUser.getNama());
        NotifkasiNoResult.setVisible(false);
        if (searched.isIskategoriSearch()) {
            List<Barang> temp = DB.getSearchResultKategori(searched.getSearch());
            if (!temp.isEmpty()) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/GraphicUserInterface/SearchCardTab.fxml"));
                    VBox Base = loader.load();
                    SearchCardTabController searchCardTabController = loader.getController();
                    searchCardTabController.SetVBox(temp);
                    VboxConatiner.getChildren().add(Base);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                NotifkasiNoResult.setVisible(true);
            }

        } else {
            List<Barang> temp = DB.getSearchResult(searched.getSearch());
            if (!temp.isEmpty()) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/GraphicUserInterface/SearchCardTab.fxml"));
                    VBox Base = loader.load();
                    SearchCardTabController searchCardTabController = loader.getController();
                    searchCardTabController.SetVBox(temp);
                    VboxConatiner.getChildren().add(Base);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                NotifkasiNoResult.setVisible(true);
            }
        }

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
    void GoToMain(MouseEvent event) throws IOException {
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

    @FXML
    void ShowSidePanel(MouseEvent event) throws IOException {

        if (SideOn) {
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
            });
            overlay.play();
            new SlideInLeft(SidePanel).setSpeed(2.0).play();
            SideOn = true;
        }

    }

}
