package com.example.willhero;
// orc hit with weapon
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class RedOrc extends Orc{
    private TranslateTransition orcjump;
    private TranslateTransition translate1;
    private ImageView redorc;
    private double health = 1;

    RedOrc(){
        super(3);
        redorc = new ImageView();
        redorc.setImage(new Image((new File("src/main/resources/RedOrc.png")).toURI().toString()));
        redorc.setFitHeight(90);
        redorc.setFitWidth(70);
        redorc.setY(346);
        redorc.setX(500);
    }


    @Override
    public void display(AnchorPane mainpane){
        mainpane.getChildren().add(redorc);
    }

    @Override
    public void remove(AnchorPane mainpane){
        mainpane.getChildren().remove(redorc);
    }

    @Override
    public void setX(double x){
        redorc.setX(x);
    }
    @Override
    public ImageView getImage(){
        return redorc;
    }

    @Override
    public void jump(){
        orcjump = Animations.translateTransition(redorc, 300,0,-70, true, -1);
//        orcjump.play();
    }

    @Override
    public void oncollide(Gameobject g){
        if (g instanceof Hero){
            if(redorc.getY() > ((Hero) g).getImage().getY()){
                ((Hero) g).fall();
                return;
            }
        }
        orcjump = Animations.translateTransition(redorc, 300,0,-80, true, -1);
        int temp;
        if(redorc.getImage() == null)return;
        if (g instanceof Weapon) {
            temp = lastspacecount;
        }
        else
        temp = ((Hero) g).getSpacecount();
        if (lastspacecount != temp) {
            lastspacecount = temp;
            orcjump.stop();
            if (g instanceof Weapon) {
                health--;
                ((Weapon) g).getHero().addcoin(1);
            }
            else if (g instanceof Hero && ((Hero) g).getCurrentweapon() != null) {
                health--;
                ((Hero) g).addcoin(1);
            }
            orcjump.stop();
            TranslateTransition translate1 = new TranslateTransition(Duration.millis(100), redorc);
            translate1.setCycleCount(1);
            translate1.setByX(60);
            translate1.setAutoReverse(false);
            translate1.play();

            TranslateTransition translate = new TranslateTransition(Duration.millis(100), redorc);
            translate.setCycleCount(1);
            translate.setToY(3);
            translate.setAutoReverse(false);
            translate.play();
            translate.setOnFinished(e1 -> {
                if (health <= 0) {
                    TranslateTransition translate2 = new TranslateTransition(Duration.millis(300), redorc);
                    translate2.setCycleCount(1);
                    translate2.setToY(150);
                    translate2.setAutoReverse(false);
                    translate2.play();
                    translate2.setOnFinished(e -> {
                        redorc.setImage(null);
                    });

                } else {
                    flagonplatform = false;
                    if (flagonplatform == false) {
                        Double t = redorc.getX();
                        int f = -1;
                        for (int i = 0; i < platforms.size(); i++) {
                            if(redorc.getBoundsInParent().intersects(platforms.get(i).getBoundsInParent())){
                                f = 0;
                                orcjump.play();
                            }
                        }
                        if (f == -1) {
                            TranslateTransition translate2 = new TranslateTransition(Duration.millis(300), redorc);
                            translate2.setCycleCount(1);
                            translate2.setToY(300);
                            translate2.setAutoReverse(false);
                            translate2.play();
                        }

                    } else {
                        orcjump.play();
                    }
                }
            });
        }
    }

}