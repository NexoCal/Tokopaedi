package Controller;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class KategoriCardController implements Initializable {

    @FXML
    private Pane ButtonPergi;

    @FXML
    private ImageView GambarKategori;

    @FXML
    private Label NamaKategori;

    private String [] kategorilist = {"OTOMOTIF","HOBI","FASHION","ELEKTRONIK"};
    private String [] KategoriImage = {};

    public void setKategori(){
        Random  picker = new Random();
        String kategoritemp;
        kategoritemp = kategorilist[picker.nextInt(kategorilist.length)];
        NamaKategori.setText(kategoritemp);
        System.out.println(NamaKategori.getText());
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Random  picker = new Random();
        String kategoritemp;
        kategoritemp = kategorilist[picker.nextInt(kategorilist.length-1)];
        NamaKategori.setText(kategoritemp);
        System.out.println(NamaKategori.getText());
    }
}
