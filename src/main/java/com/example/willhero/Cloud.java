package com.example.willhero;

import javafx.animation.Animation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;

public class Cloud{
    private ImageView cloud;
    private boolean onscreen;

    Cloud(){
        cloud = new ImageView();
        onscreen = false;
        int r = (int)(Math.random()*2+1);
        if(r == 1){
            cloud.setImage(new Image((new File("src/main/resources/cloud.png")).toURI().toString()));
        }
        else{
            cloud.setImage(new Image((new File("src/main/resources/cloud1.png")).toURI().toString()));
        }
        cloud.setX(Math.random()*600);
        cloud.setY(Math.random()*400);
        cloud.setFitHeight(70);
        cloud.setFitWidth(170);
    }

    public void setcloudx(double x){
        cloud.setX(x);
    }

    public void setcloudy(double y){
        cloud.setY(y);
    }

    public void setOnscreen(boolean onscreen) {
        this.onscreen = onscreen;
    }

    public ImageView getCloud(){
        return cloud;
    }

    public void move(AnchorPane mainpane, double start, double end, double time){
        if(!onscreen) {
            onscreen = true;
            mainpane.getChildren().add(cloud);
        }
        Animations.translateTransitionmoveto(cloud, time, start, end, false, 1).play();
    }

    public void moveetoe(AnchorPane mainpane){
        if(!onscreen) {
            onscreen = true;
            mainpane.getChildren().add(cloud);
        }
        int k1 = (int)(Math.random()*250-20);
        int k2 = (int)(Math.random()*2);
        // random number should be in a limit
        //going too up or down
        setcloudx(1000);
        if(k2 == 1){
            setcloudy(k1);
        }
        else{
            setcloudy(-1*k1);
        }
        Animations.translateTransitionmoveto(cloud, 50000, 0, -1500, false, 1).play();
    }

    public boolean collide() {
        return false;
    }
}

