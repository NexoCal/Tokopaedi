package Controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class KategoriCardTabController {

    @FXML
    private VBox BaseVBox;

    @FXML
    private HBox KategoriContainer;

    public void SetHBox() throws IOException{
        for(int i = 0; i < 2; i++){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/GraphicUserInterface/KatagoriCard.fxml"));
            Pane cardbox = loader.load();
            KategoriContainer.getChildren().add(cardbox);

        }
    }

    public VBox getVBox(){
        return BaseVBox;
    }
}