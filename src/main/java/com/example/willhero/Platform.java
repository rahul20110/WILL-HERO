package com.example.willhero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

// no platform7   add the image

public class Platform extends Gameobject{
    private static int platform_id = 0;
    private static int distance = 0;
    private ImageView platform;
    private boolean onscreen;
    private int ra = 0;
    private ArrayList<Orc> orcs = new ArrayList<Orc>();

    private double platformw[] = {450,340,300,90,300, 350, 550, 150,500,200};
    private double platformh[] = {125,130,125,120,120, 120, 120, 130,120,125};

    Platform(AnchorPane mainpane, int i){
        platform = new ImageView();
        onscreen = false;

        int r = (int)(Math.random()*8+1);
        ra = r;
        if(i == 0){                 //set first platform
            platform.setImage(new Image((new File("src/main/resources/platforml.png")).toURI().toString()));
            platform.setFitHeight(125);
            platform.setFitWidth(450);
        }
        else if(i == -1){                 //set first platform
            platform.setImage(new Image((new File("src/main/resources/platformla.png")).toURI().toString()));
            platform.setFitHeight(125);
            platform.setFitWidth(1500);
        }
        else{
            String path = "src/main/resources/platform"+r+".png";
//            System.out.println(r);
            platform.setImage(new Image((new File(path)).toURI().toString()));
            platform.setFitHeight(platformh[r]);
            platform.setFitWidth(platformw[r]);

        }
        platform_id++;
        platform.setX(400);
        platform.setY(435);
    }

    Platform(AnchorPane mainpane){
        this(mainpane, 1);
    }

    public void setPlatformx(double x){
        platform.setX(x);
    }
    @Override
    public void display(AnchorPane mainpane){
        mainpane.getChildren().add(platform);
        platform.toFront();
    }
    @Override
    public void remove(AnchorPane mainpane){
        mainpane.getChildren().remove(platform);
    }

    public void setPlatformy(double y){
        platform.setY(y);
    }

    public void setOnscreen(boolean onscreen) {
        this.onscreen = onscreen;
    }
    @Override
    public ImageView getImage(){
        return platform;
    }

    public void move(AnchorPane mainpane, double start, double end, double time){
        if(!onscreen) {
            onscreen = true;
            mainpane.getChildren().add(platform);
        }
        Animations.translateTransitionmoveto(platform, time, start, end, false, 1).play();
    }

    public void moveetoe(AnchorPane mainpane){
        if(!onscreen) {
            onscreen = true;
            mainpane.getChildren().add(platform);
        }
        int k1 = (int)(Math.random()*250-20);
        int k2 = (int)(Math.random()*2);

        setPlatformx(1000);
        if(k2 == 1){
            setPlatformy(k1);
        }
        else{
            setPlatformy(-1*k1);
        }
        Animations.translateTransitionmoveto(platform, 50000, 0, -1500, false, 1).play();
    }

    public boolean collide() {
        return false;
    }

    public static void setplatform_id(int i){
        platform_id = i;
    }

    public double getplatformw(int i){
        return platformw[ra];
    }
    @Override
    public void jump(){

    }

    @Override
    public void oncollide(Gameobject g) {

    }
}

