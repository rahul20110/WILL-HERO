package com.example.willhero;
// orc hit with weapon
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class BossOrc extends Orc{
    private TranslateTransition orcjump;
    private TranslateTransition translate1;
    private ImageView bossorc;
    private double health = 1;
    private Group won_popup;
    private AnchorPane pane;

    BossOrc(){
        super(5);
        bossorc = new ImageView();
        bossorc.setImage(new Image((new File("src/main/resources/NormalOrc.png")).toURI().toString()));
        bossorc.setFitHeight(120);
        bossorc.setFitWidth(95);
        bossorc.setY(320);
        bossorc.setX(500);
    }

    public void setwonpopup(Group w, AnchorPane p){
        won_popup = w;
        pane = p;
        bossorcdied.start();
    }

    @Override
    public void display(AnchorPane mainpane){
        mainpane.getChildren().add(bossorc);
    }

    @Override
    public void remove(AnchorPane mainpane){
        mainpane.getChildren().remove(bossorc);
    }

    @Override
    public void setX(double x){
        bossorc.setX(x);
    }
    @Override
    public ImageView getImage(){
        return bossorc;
    }

    @Override
    public void jump(){
        orcjump = Animations.translateTransition(bossorc, 300,0,-90, true, -1);
        orcjump.play();
    }

    @Override
    public void oncollide(Gameobject g) {
        if (g instanceof Hero){
            if(bossorc.getY() > ((Hero) g).getImage().getY()){
                ((Hero) g).fall();
                return;
            }
        }
        if(bossorc.getImage() == null)return;
        orcjump = Animations.translateTransition(bossorc, 300, 0, -90, true, -1);
        int temp = 0;
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
            TranslateTransition translate1 = new TranslateTransition(Duration.millis(100), bossorc);
            translate1.setCycleCount(1);
            translate1.setByX(60);
            translate1.setAutoReverse(false);
            translate1.play();

            TranslateTransition translate = new TranslateTransition(Duration.millis(100), bossorc);
            translate.setCycleCount(1);
            translate.setToY(3);
            translate.setAutoReverse(false);
            translate.play();
            translate.setOnFinished(e1 -> {
                if (health <= 0) {
                    TranslateTransition translate2 = new TranslateTransition(Duration.millis(300), bossorc);
                    translate2.setCycleCount(1);
                    translate2.setToY(150);
                    translate2.setAutoReverse(false);
                    translate2.play();
                    translate2.setOnFinished(e -> {
                        bossorc.setImage(null);
                        if(g instanceof Hero)((Hero) g).addcoin(5);
                        else if(g instanceof Weapon){
                            ((Weapon) g).getHero().addcoin(5);
                        }
                    });

                } else {
                    flagonplatform = false;
                    if (flagonplatform == false) {
                        Double t = bossorc.getX();
                        int f = -1;
                        for (int i = 0; i < platforms.size(); i++) {
                            if(bossorc.getBoundsInParent().intersects(platforms.get(i).getBoundsInParent())){
                                f = 0;
                                orcjump.play();
                            }
                        }
                        if (f == -1) {
                            TranslateTransition translate2 = new TranslateTransition(Duration.millis(300), bossorc);
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

    AnimationTimer bossorcdied  = new AnimationTimer() {
        @Override
        public void handle(long l) {
            if(health <= 0){
                won_popup.toFront();
                TranslateTransition translate = new TranslateTransition(Duration.millis(400), won_popup);
                translate.setToX(-(pane.getPrefWidth() + ((Node) won_popup).getBoundsInLocal().getWidth()) / 2);
                translate.play();
                bossorcdied.stop();
            }
        }
    };

}
