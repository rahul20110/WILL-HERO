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

public abstract class Orc extends Gameobject{
    protected TranslateTransition orcjump;
    protected TranslateTransition translate1;
//    protected ImageView orc;
    protected double health = 1;

    protected static ArrayList<Double> platformstarts = new ArrayList<Double>();
    protected static ArrayList<Double> platformsize = new ArrayList<Double>();
    protected static ArrayList<ImageView> platforms = new ArrayList<ImageView>();
    protected boolean flagonplatform = true;
    protected int lastspacecount = 0;
    protected boolean flaghit = false;

    Orc(double he){
        this.health = he;
    }

    public static void addplaform(ImageView i){
        platforms.add(i);
    }

    public static void addplatformd(double i, double j){
        platformstarts.add(i);
        platformsize.add(j);
    }


    public abstract void jump();

    public abstract void remove(AnchorPane mainpane);

    public abstract void setX(double x);

    public abstract ImageView getImage();

    public double gethealth(){
        return health;
    }


}
