package com.example.willhero;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.File;

public class Sword extends Weapon{

    private TranslateTransition jumpwithhero;

    public Sword(Hero h){
        super(h);
        weapon = new ImageView();
        weapon.setImage(new Image((new File("src/main/resources/Sword.png")).toURI().toString()));
        weapon.setFitHeight(15);
        weapon.setFitWidth(70);
        weapon.setY(410);
        weapon.setX(300);
        jumpwithhero = Animations.translateTransition(weapon, 300,0,-70, true, -1);
    }

    public void move(AnchorPane pane){
        TranslateTransition movefor = Animations.translateTransition(weapon, 100, 20, 0, false, 1);
        movefor.play();
        movefor.setOnFinished(e-> {
            RotateTransition rota = new RotateTransition(Duration.millis(100), weapon);
            rota.setByAngle(360);
            rota.setCycleCount(1);
            rota.setAutoReverse(false);
            rota.play();
            rota.setOnFinished(e1-> {
                TranslateTransition moveback = Animations.translateTransition(weapon, 100, -20, 0, false, 1);
                moveback.play();
            });
        });
    }

    public void movewithhero(){
        jumpwithhero.play();
    }

    public void stopmovewithhero(){
        jumpwithhero.stop();
    }
}
