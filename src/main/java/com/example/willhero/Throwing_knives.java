package com.example.willhero;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

public class Throwing_knives extends Weapon {
    private double distance =1;


    Throwing_knives(Hero h){
        super(h);
        weapon = new ImageView();
        weapon.setImage(new Image((new File("src/main/resources/Knife.png")).toURI().toString()));
        weapon.setFitHeight(8);
        weapon.setFitWidth(60);
        weapon.setY(420);
        weapon.setX(325);
//        weaponcopy = new ImageView();
//        weaponcopy.setImage(new Image((new File("src/main/resources/Knife.png")).toURI().toString()));
//        weaponcopy.setFitHeight(8);
//        weaponcopy.setFitWidth(60);
//        weaponcopy.setY(weapon.getY());
//        weaponcopy.setX(weapon.getX());
//        jumpwithheroc = Animations.translateTransition(weaponcopy, 300,0,-70, true, -1);
        jumpwithhero = Animations.translateTransition(weapon, 300,0,-70, true, -1);
    }

    @Override
    public void display(AnchorPane mainpane) {
        super.display(mainpane);
    }

    public void move(AnchorPane pane) {

        TranslateTransition movefor = Animations.translateTransition(weapon, 100/speed, 100*distance, 0, false, 1);
        movefor.play();
        movefor.setOnFinished(e-> {
            TranslateTransition moveback = Animations.translateTransition(weapon, 100/speed, -100*distance, 0, false, 1);
            moveback.play();
        });
    }


    public void setDamage() {
        super.setDamage();
        if(distance <= 2){
            this.distance += 0.2;
        }
    }



    public void stopmovewithhero(){
        jumpwithhero.stop();
//        jumpwithheroc.stop();
    }

    public void movewithhero(){
        jumpwithhero.play();
//        jumpwithheroc.play();
    }


}
