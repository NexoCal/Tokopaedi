import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdminInterface extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GraphicUserInterface/AdminOnlyScreenBarang.fxml"));
        Scene scene = new Scene(loader.load());

        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("");
        stage.show();
        
    }
    
    public static void main(String[] args) {
        Platform.setImplicitExit(false);
        launch(args);
    }
    
}

