package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class SidePaneSetterController implements Initializable {

    @FXML
    private Pane BasePane;

    @FXML
    private ScrollPane BaseScroll;

    public ScrollPane getScrollPane(){
        return BaseScroll;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/GraphicUserInterface/SideBar.fxml"));
            AnchorPane cardbox;
            try {
                cardbox = loader.load();
                BasePane.getChildren().add(cardbox);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

    }
}
