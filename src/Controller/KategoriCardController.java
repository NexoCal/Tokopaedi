package Controller;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class KategoriCardController implements Initializable {

    @FXML
    private Pane ButtonPergi;

    @FXML
    private ImageView GambarKategori;

    @FXML
    private Label NamaKategori;

    static private ArrayList <String[]> kategorilist = new ArrayList<>();
    private String[] Otomotif = {"OTOMOTIF","/ImageAsset/Foto kategori/otomotif b.jpg"};
    private String[] Hobi = {"HOBI","/ImageAsset/Foto kategori/gundame.png"};
    private String[] Fashion = {"FASHION","/ImageAsset/Foto kategori/fashion.jpg"};
    private String[] Elektronik = {"ELEKTRONIK","/ImageAsset/Foto kategori/electronic.jpg"};
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        kategorilist.add(Otomotif);
        kategorilist.add(Elektronik);
        kategorilist.add(Fashion);
        kategorilist.add(Hobi);
        
        Random  picker = new Random();
        String [] kategoritemp;
        kategoritemp = kategorilist.get(picker.nextInt(kategorilist.size()));
        NamaKategori.setText(kategoritemp[0]);
        Image x = new Image(kategoritemp[1]);
        GambarKategori.setImage(x);
        System.out.println(NamaKategori.getText());

        kategorilist.remove(kategorilist.indexOf(kategoritemp));
    }
}
