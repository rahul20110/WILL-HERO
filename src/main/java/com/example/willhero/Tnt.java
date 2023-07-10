package com.example.willhero;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.File;
import java.net.HttpRetryException;
import java.util.concurrent.atomic.AtomicInteger;

public class Tnt extends Gameobject{
    private ImageView tnt;
    private boolean blasted=false;
    private int lastspacecount;
    private String lastobj = "";

    public Tnt(){
        tnt = new ImageView();
        tnt.setImage(new Image((new File("src/main/resources/Tnt.png")).toURI().toString()));
        tnt.setFitHeight(50);
        tnt.setFitWidth(38);
        tnt.setY(386);
        tnt.toFront();
    }

    @Override
    public void display(AnchorPane mainpane){
        mainpane.getChildren().add(tnt);
    }

    public void setX(double x){
        tnt.setX(x);
    }

    @Override
    public void oncollide(Gameobject g) {
        if (blasted == false && tnt.getImage() != null) {
            blasted = true;

            AtomicInteger t = new AtomicInteger(0);
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), e->{
                if(t.get() == 0) {
                    t.incrementAndGet();
                }
                else if(t.get() == 1){
                    t.incrementAndGet();
                    tnt.setImage(new Image((new File("src/main/resources/Tntblast.png")).toURI().toString()));
                    tnt.setFitHeight(150);
                    tnt.setFitWidth(150);
                    tnt.setY(350);
                    Hero h = null;
                    if (g instanceof Weapon) {
                        h = ((Weapon) g).getHero();
                    } else if (g instanceof Hero) {
                        h = ((Hero) g);
                    }
                    if(h.getImage().getBoundsInParent().intersects(tnt.getBoundsInParent())){
                    h.fall();
                }
                }
                else{
                    tnt.setImage(null);
                }
            }));
            timeline.setCycleCount(3);
            timeline.play();
        }
    }

    public void jump(){
//        tnt = Animations.translateTransition(tnt, 300,0,-5, true, -1);
//        tnt.play();
    }

    public ImageView getImage(){
        return tnt;
    }

    @Override
    public void remove(AnchorPane mainpane) {
        mainpane.getChildren().remove(tnt);
    }
}
