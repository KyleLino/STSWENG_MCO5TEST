package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    public void startGame(ActionEvent actionEvent) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/gameView.fxml"));
            Stage stage = new Stage();
            stage.setTitle("CCPROG3 MP");
            stage.setScene(new Scene(root, 800, 600));
            stage.setResizable(false);
            stage.show();
            close(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openMainMenu(ActionEvent actionEvent) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/mainView.fxml"));
            Stage stage = new Stage();
            stage.setTitle("MAIN MENU");
            stage.setScene(new Scene(root, 800, 600));
            stage.setResizable(false);
            stage.show();
            close(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    public void exitGame(ActionEvent actionEvent) {
        System.exit(0);
    }

}
