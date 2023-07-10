package com.example.willhero;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class GreetingControler {

    @FXML
    private Parent root;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;

    @FXML
    private AnchorPane greeting_page;
    @FXML
    private ImageView play_button;

    @FXML
    void gotogame(MouseEvent event)  throws IOException {
        root = FXMLLoader.load(getClass().getResource("game.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, Color.rgb(29,200,255,1));
        scene.setFill(Color.rgb(29,200,255,1));

        stage.setScene(scene);
        stage.show();
    }
}