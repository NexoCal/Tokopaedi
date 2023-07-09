package Controller;

import java.io.IOException;
import java.util.List;

import Model.Barang;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RecommendedTabCardController {
    int count = 1;
    int Data = 1;
    int height = 249;
    boolean resize = false;
    
    @FXML
    private HBox Base;

    @FXML
    private VBox Vbox1;

    @FXML
    private VBox Vbox2;

    @FXML
    private VBox Vbox3;

    @FXML
    private VBox Vbox4;

    @FXML
    private VBox Vbox5;

    @FXML
    private VBox Vbox6;

    public void setGridbyIDs(List<Integer> ID) throws IOException{
        for(int i = 0; i < ID.size(); i++){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/GraphicUserInterface/CardRecommend.fxml"));
            VBox cardbox = loader.load();
            CardRecommendController cardRecommendController = loader.getController();
            cardRecommendController.setDataBarang(ID.get(i));
            switch (count){
                case 1:
                    Vbox1.getChildren().add(cardbox);
                    if (resize){
                        int x = (int) Base.getPrefHeight();
                        Base.setPrefHeight(x+height);
                        resize = false;
                    }
                    count++;
                    break;
                case 2:
                    Vbox2.getChildren().add(cardbox);
                    count++;
                    break;
                case 3:
                    Vbox3.getChildren().add(cardbox);
                    count++;
                    break;
                case 4:
                    Vbox4.getChildren().add(cardbox);
                    count++;
                    break;
                case 5:
                    Vbox5.getChildren().add(cardbox);
                    count++;
                    break;
                case 6:
                    Vbox6.getChildren().add(cardbox);
                    resize = true;
                    count = 1;
                    break;

            
            }
            
            

        }
    }

    public void setGridbyDaftarBarang(List<Barang> brg) throws IOException{
        for(Barang barang : brg){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/GraphicUserInterface/CardRecommend.fxml"));
            VBox cardbox = loader.load();
            CardRecommendController cardRecommendController = loader.getController();
            cardRecommendController.setDataBarang(barang);
            switch (count){
                case 1:
                    Vbox1.getChildren().add(cardbox);
                    if (resize){
                        int x = (int) Base.getPrefHeight();
                        Base.setPrefHeight(x+height);
                        resize = false;
                    }
                    count++;
                    break;
                case 2:
                    Vbox2.getChildren().add(cardbox);
                    count++;
                    break;
                case 3:
                    Vbox3.getChildren().add(cardbox);
                    count++;
                    break;
                case 4:
                    Vbox4.getChildren().add(cardbox);
                    count++;
                    break;
                case 5:
                    Vbox5.getChildren().add(cardbox);
                    count++;
                    break;
                case 6:
                    Vbox6.getChildren().add(cardbox);
                    resize = true;
                    count = 1;
                    break;

            
            }
            
            

        }
    }

    public HBox getGrid(){
        return Base;
    }

}
