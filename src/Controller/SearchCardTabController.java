package Controller;

import java.io.IOException;
import java.util.List;

import Model.Barang;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SearchCardTabController {

    @FXML
    private VBox BaseVBox;


    public void SetVBox(List<Barang> temp) throws IOException {
        for(Barang barang : temp){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/GraphicUserInterface/SearchCard.fxml"));
            HBox cardbox = loader.load();
            SearchCardController searchCardController = loader.getController();
            searchCardController.setCard(barang);

            BaseVBox.getChildren().add(cardbox);

        }
            

    }

    public VBox getBaseVBox(){
        return BaseVBox;
    }
    
}
