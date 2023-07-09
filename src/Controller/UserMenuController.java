package Controller;

import java.io.IOException;

import animatefx.animation.SlideInRight;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class UserMenuController {

    @FXML
    void Exit(MouseEvent event) {

    }

    @FXML
    void GoToKatalogManagement(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GraphicUserInterface/KatalogManagePage.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Tokopaedi");
        stage.show();

        new SlideInRight(root).setSpeed(1.7).play();;

        ((Parent) event.getSource()).getScene().getWindow().hide();

    }

    @FXML
    void GoToProfile(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GraphicUserInterface/ProfilePage.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Tokopaedi");
        stage.show();

        new SlideInRight(root).setSpeed(1.7).play();

        ((Parent) event.getSource()).getScene().getWindow().hide();
    }

}
