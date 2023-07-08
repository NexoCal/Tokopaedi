package Controller;

import java.io.IOException;
import java.util.List;

import Model.Barang;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RecentTabCardController {
    int Recent = 10;

    @FXML
    private HBox HboxContainer;

    public void SetHBox(List<Integer> ID) throws IOException {
        int x = ID.size() - Recent;
        if (x < 0) {
            for(int i = ID.size(); i > 0; i--){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/GraphicUserInterface/RecentProductCard.fxml"));
            HBox cardbox = loader.load();
            RecentCardController recentCardController = loader.getController();
            recentCardController.setDataBarang(ID.get(i-1));

            HboxContainer.getChildren().add(cardbox);

        }

        } else {
            for (int i = ID.size(); i > ID.size() - Recent; i--) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/GraphicUserInterface/RecentProductCard.fxml"));
                HBox cardbox = loader.load();
                RecentCardController recentCardController = loader.getController();
                recentCardController.setDataBarang(ID.get(i-1));

                HboxContainer.getChildren().add(cardbox);

            }

        }

    }

    public HBox getHBox() {
        return HboxContainer;
    }

}
