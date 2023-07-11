package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import Model.SearchListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class KategoriCardController implements Initializable {
    Scene scene;
    Stage stage;

    @FXML
    private Pane ButtonPergi;

    @FXML
    private ImageView GambarKategori;

    @FXML
    private Label NamaKategori;

    static private ArrayList<String[]> kategorilist = new ArrayList<>();
    private String[] Otomotif = { "OTOMOTIF", "/ImageAsset/Foto kategori/otomotif b.jpg" };
    private String[] Hobi = { "HOBI", "/ImageAsset/Foto kategori/gundame.png" };
    private String[] Fashion = { "FASHION", "/ImageAsset/Foto kategori/fashion.jpg" };
    private String[] Elektronik = { "ELEKTRONIK", "/ImageAsset/Foto kategori/electronic.jpg" };

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        kategorilist.add(Otomotif);
        kategorilist.add(Elektronik);
        kategorilist.add(Fashion);
        kategorilist.add(Hobi);

        Random picker = new Random();
        String[] kategoritemp;
        kategoritemp = kategorilist.get(picker.nextInt(kategorilist.size()));
        NamaKategori.setText(kategoritemp[0]);
        Image x = new Image(kategoritemp[1]);
        GambarKategori.setImage(x);
        System.out.println(NamaKategori.getText());

        kategorilist.remove(kategorilist.indexOf(kategoritemp));
    }

    @FXML
    void SeacrhKatalog(MouseEvent event) throws IOException {
        SearchListener temp = new SearchListener();
        temp.setIskategoriSearch(true);
        if (NamaKategori.getText().equals("FASHION")) {
            temp.setSearch("Fashion");
        } else if (NamaKategori.getText().equals("ELEKTRONIK")) {
            temp.setSearch("Elektronik");
        } else if (NamaKategori.getText().equals("HOBI")) {
            temp.setSearch("Hobi & Olahraga");
        } else {
            temp.setSearch("Otomotif");
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GraphicUserInterface/SearchResultPage.fxml"));
        scene = new Scene(loader.load());
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Tokopaedi");
        stage.show();

    }
}
