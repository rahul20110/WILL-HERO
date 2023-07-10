package com.example.willhero;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;

public class Chest extends Gameobject{
    private static int count = 1;
    private ImageView chest;
    private TranslateTransition chestjump;
    private boolean opened =  false;
    private int id;

    public Chest(){
        chest = new ImageView();
        chest.setImage(new Image((new File("src/main/resources/ChestClosed.png")).toURI().toString()));
        chest.setFitHeight(55);
        chest.setFitWidth(70);
        chest.setY(383);
        id = count;
        count++;
    }
    @Override
    public void display(AnchorPane mainpane){
        mainpane.getChildren().add(chest);
        chest.toFront();
    }

    public void setX(double x){
        chest.setX(x);
    }
    @Override
    public void oncollide(Gameobject g){
        if(opened == false){
        if(g instanceof Hero ){
                chest.setImage(null);
                chest.setImage(new Image((new File("src/main/resources/ChestOpen.png")).toURI().toString()));
                if(id == 1){
                    ((Hero)g).addweapon(1);
                }
                else if(id == 2){
                    ((Hero)g).addweapon(0);
                }
                else if(((int)(Math.random()*3) == 1) ){
                    ((Hero)g).addweapon((int)(Math.random()*2));
                }
                else{
                    ((Hero)g).addcoin(5);
                }
                opened = true;
            }
        else if(g instanceof Weapon){
            chest.setImage(null);
            chest.setImage(new Image((new File("src/main/resources/ChestOpen.png")).toURI().toString()));
                if((int)(Math.random()*3) == 1){
            ((Weapon) g).getHero().addweapon((int)(Math.random()*2));
//            ((Weapon) g).getHero().addweapon(0);
                }
                else{
                    ((Weapon) g).getHero().addcoin(5);
                }
            opened = true;
        }
        else{
            TranslateTransition movefor = Animations.translateTransition(chest, 100, 70, 0, false, 1);
            movefor.play();
        }
        }
    }
    @Override
    public void jump(){
        chestjump = Animations.translateTransition(chest, 300,0,-5, true, -1);
        chestjump.play();
    }
    @Override
    public ImageView getImage(){
        return chest;
    }

    @Override
    public void remove(AnchorPane mainpane) {
            mainpane.getChildren().remove(chest);
    }
}
