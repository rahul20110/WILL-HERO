package com.example.willhero;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javafx.scene.control.Label;
import java.io.File;
import java.util.ArrayList;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.util.concurrent.atomic.AtomicBoolean;

public class Hero extends Gameobject {
    private int coin_collected = 0;
    private Weapon[] w = new Weapon[2];
    private Weapon currentweapon = null;

    private AnchorPane rootmain;
    private ArrayList<Double> platformstarts = new ArrayList<Double>();
    private ArrayList<Double> platformsize = new ArrayList<Double>();

    private TranslateTransition herojump;
    private ImageView hero;

    private boolean flagonplatform = true;
    private int currentplatform = 1;
    private AtomicBoolean another_space = new AtomicBoolean(false);
    private boolean flagexit = false;
    private boolean another_space1 = false;
    private int spacecount = 0;
    Label coincount=new Label();
    Label stepcountl = new Label();

    private ImageView knives_logo;
    private ImageView sword_logo;


    Hero(AnchorPane mainpane){
        rootmain = mainpane;
        hero = new ImageView();
        hero.setImage(new Image((new File("src/main/resources/knight.png")).toURI().toString()));
        hero.setFitHeight(50);
        hero.setFitWidth(38);
        hero.setY(386);
        hero.setX(320);
        mainpane.getChildren().add(hero);

        coincount.setLayoutX(900.0);
        coincount.setLayoutY(18.0);
        coincount.setPrefWidth(67.0);
        coincount.setPrefHeight(46.0);
        coincount.minWidth(Region.USE_COMPUTED_SIZE);
        coincount.minHeight(Region.USE_COMPUTED_SIZE);
        coincount.maxHeight(Region.USE_COMPUTED_SIZE);
        coincount.maxWidth(Region.USE_COMPUTED_SIZE);
        Font font = Font.font("Verdana", FontWeight.EXTRA_BOLD, 25);
        coincount.setFont(font);
        coincount.setTextFill(Paint.valueOf("#fcfcfc"));
        rootmain.getChildren().add(coincount);

        //set stepcount label
        stepcountl.setLayoutX(400);
        stepcountl.setLayoutY(18.0);
        stepcountl.setPrefWidth(170);
        stepcountl.setPrefHeight(46.0);
        stepcountl.minWidth(Region.USE_COMPUTED_SIZE);
        stepcountl.minHeight(Region.USE_COMPUTED_SIZE);
        stepcountl.maxHeight(Region.USE_COMPUTED_SIZE);
        stepcountl.maxWidth(Region.USE_COMPUTED_SIZE);
        Font font1 = Font.font("Verdana", FontWeight.EXTRA_BOLD, 25);
        stepcountl.setFont(font1);
        stepcountl.setTextFill(Paint.valueOf("#fcfcfc"));
        rootmain.getChildren().add(stepcountl);
        coincount.setText(Integer.toString(coin_collected));
        coincount.toBack();
    }

    @Override
    public void display(AnchorPane mainpane){
        mainpane.getChildren().add(hero);
    }

    public void addcoin(int x){
        coin_collected += x;
        coincount.setText(Integer.toString(coin_collected));
    }

    public int getCoin_collected(){
        return coin_collected;
    }

    public void setimages(ImageView knives_logo, ImageView sword_logo){
        this.knives_logo = knives_logo;
        this.sword_logo =sword_logo;
        knives_logo.toFront();
        sword_logo.toFront();
    }

    public void addweapon(int i){
        if(w[i] == null){
            if(i == 0) {
                w[i] = new Sword(this);
                sword_logo.setImage(new Image((new File("src/main/resources/swordactive.jpg")).toURI().toString()));
            }
            else {
                w[i] = new Throwing_knives(this);
                knives_logo.setImage(new Image((new File("src/main/resources/knivesactive.jpg")).toURI().toString()));
            }
            if(currentweapon != null)currentweapon.remove(rootmain);
            currentweapon = w[i];
            if(currentweapon != null)currentweapon.display(rootmain);
        }
        else {
            w[i].setDamage();
            w[i].setSpeed();
        }
    }

    public void changeweapon(int i){
        if(w[i] != null){
            if(currentweapon != null)currentweapon.remove(rootmain);
            currentweapon = w[i];
            if(currentweapon != null)currentweapon.display(rootmain);

//            TranslateTransition translate = new TranslateTransition(Duration.millis(300),hero);
//            translate.setCycleCount(1);
//            translate.setToY(3);
//            translate.setAutoReverse(false);
//            translate.setOnFinished(e ->{
//                if(currentweapon != null)currentweapon.remove(rootmain);
//                currentweapon = w[i];
//                if(currentweapon != null)currentweapon.display(rootmain);
//            });
//            translate.play();
        }
    }
    public void setcurrentweapon(int i){
        currentweapon = w[i];
    }
    public void update(){
        coincount.setText(Integer.toString(coin_collected));
        coincount.toFront();
    }
    public void addplatformd(double i, double j){
        platformstarts.add(i);
        platformsize.add(j);
        Orc.addplatformd(i,j);
    }

    @Override
    public void jump(){
        herojump = Animations.translateTransition(hero, 300,0,-70, true, -1);
        herojump.play();
        if(currentweapon != null) currentweapon.movewithhero();
    }

    public void moveforward(){
        flagonplatform = false;
        herojump.stop();
        if(currentweapon != null) {
            currentweapon.stopmovewithhero();
            currentweapon.move(rootmain);
        }
        TranslateTransition movefor = Animations.translateTransition(hero, 70, 0, 0, false, 1);
        TranslateTransition translate = new TranslateTransition(Duration.millis(300),hero);
        translate.setCycleCount(1);
        translate.setToY(3);
        translate.setAutoReverse(false);
        movefor.setOnFinished(e -> {
            TranslateTransition translateup = new TranslateTransition(Duration.millis(60), hero);
            translateup.setCycleCount(1);
            translateup.setByY(-50);
            translateup.setAutoReverse(false);
            translateup.play();
            if(currentweapon != null)currentweapon.gotoplatform();

            translate.play();
        });
        movefor.play();
        translate.setOnFinished(e -> {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            if(flagonplatform == false ){
                Double t = hero.getX();
                int f = -1;
                for(int i = currentplatform; i < platformstarts.size();i++) {
                    if (Double.compare(platformstarts.get(i) - spacecount*(200), t) < 0 && Double.compare(platformstarts.get(i+1) - spacecount*(200), t) > 0) {
                        if (Double.compare((platformstarts.get(i) -spacecount*(200) + platformsize.get(i)), t) > 30) {
                            herojump.play();
                            if(currentweapon != null)currentweapon.movewithhero();
                            f = 0;
                            currentplatform = i;
                            break;
                        }
                    }
                }
                if(f == -1){
                    flagexit = true;
                    TranslateTransition translate1 = new TranslateTransition(Duration.millis(300),hero);
                    translate1.setCycleCount(1);
                    translate1.setToY(300);
                    translate1.setAutoReverse(false);
                    translate1.play();
                    if(currentweapon != null)currentweapon.fallwithhero();
                    }
            }
                else{
                    herojump.play();
                    if(currentweapon != null)currentweapon.movewithhero();
            }
        });
    }

    @Override
    public ImageView getImage(){
        return hero;
    }


    public void setonplatform(boolean f){
        flagonplatform = f;
    }

    public boolean isFlagexit() {
        return flagexit;
    }

    public void setAnother_space() {
        another_space1 = true;
        this.another_space.set(true);
        spacecount++;
        coincount.setText(Integer.toString(coin_collected));
        stepcountl.setText("Score :  " +Integer.toString(spacecount));
        coincount.toFront();
        stepcountl.toFront();
    }

    public void moveback(){
        Animations.translateTransition(hero, 3000, -70, 0, false, 1).play();
    }

    public void setcuurentplatform(int i){
        currentplatform = i;
    }

    public Weapon getCurrentweapon(){
        return currentweapon;
    }
    @Override
    public void oncollide(Gameobject g){

    }
    @Override
    public void remove(AnchorPane mainpane) {
    }

    public int getSpacecount() {
        return spacecount;
    }


    public int resurrection(){
        if(coin_collected >= 20){
            Double t = hero.getX();
            for(int i = currentplatform; i < platformstarts.size();i++) {
                if (Double.compare(platformstarts.get(i) - spacecount*(200), t) < 0 && Double.compare(platformstarts.get(i+1) - spacecount*(200), t) > 0) {
                    return i+1;
                }
            }
        }
        return -1;
    }

    public void setXY(){
        flagexit = false;
        flagonplatform = true;
        TranslateTransition translate1 = new TranslateTransition(Duration.millis(2),hero);
        translate1.setCycleCount(1);
        translate1.setToY(0);
        translate1.setAutoReverse(false);
        translate1.play();
        if(currentweapon != null)currentweapon.resurrect();
//        TranslateTransition new = TranslateTransition()
    }

    public void fall(){
        flagexit = true;
        TranslateTransition translate1 = new TranslateTransition(Duration.millis(300),hero);
        translate1.setCycleCount(1);
        translate1.setToY(300);
        translate1.setAutoReverse(false);
        translate1.play();
        if(currentweapon != null)currentweapon.fallwithhero();
        herojump.stop();
    }
}
