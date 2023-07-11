package Controller;

import java.io.IOException;

import Model.SearchListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SidePaneController {

    private Stage stage;
    private Scene scene;

    @FXML
    private Pane AkunButton;

    @FXML
    private Pane Elektronik;

    @FXML
    private Pane Hobi;

    @FXML
    private Button JualBarangButton;

    @FXML
    private Pane Otomotif;

    @FXML
    private Pane Pakaian;

    @FXML
    private TextField SearchBar;

    @FXML
    void GoToAccount(MouseEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GraphicUserInterface/ProfilePage.fxml"));
            scene = new Scene(loader.load());
            stage = (Stage)((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);
            stage.setResizable(false);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("Tokopaedi");
            stage.show();
    }

    @FXML
    void GoToElektonik(MouseEvent event) throws IOException {

        SearchListener temp = new SearchListener();
            temp.setIskategoriSearch(true);
            temp.setSearch("Elektronik");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GraphicUserInterface/SearchResultPage.fxml"));
            scene = new Scene(loader.load());
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            stage.setScene(scene);
            stage.setResizable(false);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("Tokopaedi");
            stage.show();

    }

    @FXML
    void GoToHobi(MouseEvent event) throws IOException {

        SearchListener temp = new SearchListener();
            temp.setIskategoriSearch(true);
            temp.setSearch("Hobi & Olahraga");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GraphicUserInterface/SearchResultPage.fxml"));
            scene = new Scene(loader.load());
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            stage.setScene(scene);
            stage.setResizable(false);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("Tokopaedi");
            stage.show();

    }

    @FXML
    void GoToKatalogManage(MouseEvent event) throws IOException {



    }

    @FXML
    void GoToOtomotif(MouseEvent event) throws IOException {

        SearchListener temp = new SearchListener();
            temp.setIskategoriSearch(true);
            temp.setSearch("Otomotif");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GraphicUserInterface/SearchResultPage.fxml"));
            scene = new Scene(loader.load());
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            stage.setScene(scene);
            stage.setResizable(false);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("Tokopaedi");
            stage.show();

    }

    @FXML
    void GoToPakaian(MouseEvent event) throws IOException {

            SearchListener temp = new SearchListener();
            temp.setIskategoriSearch(true);
            temp.setSearch("Fashion");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GraphicUserInterface/SearchResultPage.fxml"));
            scene = new Scene(loader.load());
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            stage.setScene(scene);
            stage.setResizable(false);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("Tokopaedi");
            stage.show();

    }

    @FXML
    void SearchBarang(KeyEvent event) {

    }

}
