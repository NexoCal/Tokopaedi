package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class KatalogManageController implements Initializable {
    DatabaseModel DB = new DatabaseModel();

    @FXML
    private VBox VboxContainer;

    @FXML
    private Pane Upper;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/GraphicUserInterface/KatalogManageTab.fxml"));
            HBox Base = loader.load();
            KatalogCarTabController kategoriCardTabController = loader.getController();
            kategoriCardTabController.setTab();
            VboxContainer.getChildren().add(Base);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

}
