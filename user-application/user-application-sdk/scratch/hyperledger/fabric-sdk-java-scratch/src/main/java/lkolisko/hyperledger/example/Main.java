package lkolisko.hyperledger.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static String argumentIP;

    public static void main(String[] args) {
        if (args.length > 0) {
            argumentIP = args[0];
        }
        else{
            argumentIP = "no";
        }
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Login.fxml"));
        Parent root = loader.load();

        Controller controller = loader.getController();
        controller.setIP(argumentIP);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);

        primaryStage.setTitle("로그인");
        primaryStage.show();
    }
}
