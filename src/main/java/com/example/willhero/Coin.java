package com.example.willhero;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


import java.io.File;

public class Coin extends Gameobject{
    private ImageView coin;
    private TranslateTransition coinjump;
    private boolean correct=true;

    public Coin(){
        coin = new ImageView();
        coin.setImage(new Image((new File("src/main/resources/Coin.png")).toURI().toString()));
        coin.setFitHeight(30);
        coin.setFitWidth(30);
        coin.setY(380);
        coin.toFront();
    }
    @Override
    public void display(AnchorPane mainpane){
        mainpane.getChildren().add(coin);
    }

    public void setX(double x){
        coin.setX(x);
    }
    @Override
    public void oncollide(Gameobject g){
        if(correct==true && coin.getImage() != null){
            correct = false;
            if(g instanceof Hero){
                coin.setImage(null);
                ((Hero)g).addcoin(1);
                ((Hero) g).update();

            }
            if(g instanceof Weapon){
                coin.setImage(null);
                ((Weapon) g).getHero().addcoin(1);
                ((Weapon) g).getHero().update();
            }
            else{
                TranslateTransition movefor = Animations.translateTransition(coin, 100, 70, 0, false, 1);
                movefor.play();
            }
        }
    }

    @Override
    public void jump(){
        coinjump = Animations.translateTransition(coin, 300,0,-5, true, -1);
        coinjump.play();
    }
    @Override
    public ImageView getImage(){
        return coin;
    }

    @Override
    public void remove(AnchorPane mainpane) {
        mainpane.getChildren().remove(coin);
    }
}
