package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Model.Barang;
import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
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
            recentTabCardController.SetHBox(IDs);
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
            recommendedTabCardController.setGrid(IDs);
            VboxCardContainer.getChildren().add(Base);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private List<Barang> allproduk() {
        DB.ConnectToDataBase("src/TokopaediDatabase.db");
        List<Integer> ItemIDs = DB.SelectAllID();
        List<Barang> temp = new ArrayList<Barang>();
        int x = DB.CountDataBarang();

        for (int i = 0; i < x; i++) {
            Barang tempBarang = DB.SelectBarang(i);
            temp.add(tempBarang);
        }
        DB.DisconnectFromDataBase();
        return temp;
    }

    private List<Integer> GetIDs(){
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

}
