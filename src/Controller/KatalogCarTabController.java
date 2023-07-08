package Controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class KatalogCarTabController {
    DatabaseModel DB = new DatabaseModel();
    int height = 468;

    @FXML
    private HBox BaseHBox;

    @FXML
    private VBox VBox1;

    @FXML
    private VBox VBox2;

    @FXML
    private VBox VBox3;

    public void setTab() throws IOException {
        DB.ConnectToDataBase("src/TokopaediDatabase.db");
        int count = DB.CountDataBarang();
        int Column = 1;

        for (int i = 1; i <= count; i++) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/GraphicUserInterface/KatalogCard.fxml"));
            VBox cardbox = loader.load();
            KatalogCardController katalogCardController = loader.getController();
            katalogCardController.setData(i);
            if (katalogCardController.same) {
                switch (Column) {
                    case 1:
                        VBox1.getChildren().add(cardbox);
                        Column++;
                        break;
                    case 2:
                        VBox2.getChildren().add(cardbox);
                        Column++;
                        break;
                    case 3:
                        VBox3.getChildren().add(cardbox);
                        Column = 1;
                        break;
                }
            }

        }
        FXMLLoader loaderAdd = new FXMLLoader();
        loaderAdd.setLocation(getClass().getResource("/GraphicUserInterface/KatalogCardAdd.fxml"));
        Pane cardboxAdd = loaderAdd.load();

        switch (Column) {
            case 1:
                VBox1.getChildren().add(cardboxAdd);
                Column++;
                break;
            case 2:
                VBox2.getChildren().add(cardboxAdd);
                Column++;
                break;
            case 3:
                VBox3.getChildren().add(cardboxAdd);
                Column = 1;
                break;

        }
    }

    public HBox getHBox() {
        return BaseHBox;
    }

}
