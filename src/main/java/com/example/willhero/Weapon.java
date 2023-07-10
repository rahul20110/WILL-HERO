package com.example.willhero;

import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.File;

public abstract class Weapon extends Gameobject {
    protected ImageView weapon;
    protected Hero hero;
    protected TranslateTransition jumpwithhero;
    protected AnchorPane pane;
    protected double speed = 1.0;
    protected double damage = 1.0;

    public Weapon(Hero h){
        hero = h;
    }

    public void jump(){

    }

    public void oncollide(Gameobject g){

    }


    public void setDamage() {
        this.damage += 0.2;
    }

    public double getDamage() {
        return damage;
    }

    public void setSpeed() {
        this.speed += 0.2;
    }

    public double getSpeed() {
        return speed;
    }

    public Hero getHero() {
        return hero;
    }

    public void display(AnchorPane mainpane){
        mainpane.getChildren().add(weapon);
        pane = mainpane;
    }

    public void setX(double x){
        weapon.setX(x);
    }

    public ImageView getImage(){
        return weapon;
    }

    public void remove(AnchorPane mainpane){
        mainpane.getChildren().remove(weapon);
    }

    public void gotoplatform(){
        TranslateTransition translate = new TranslateTransition(Duration.millis(300),weapon);
        translate.setCycleCount(1);
        translate.setToY(3);
        translate.setAutoReverse(false);
        translate.play();
    }

    public void fallwithhero(){
        TranslateTransition translate1 = new TranslateTransition(Duration.millis(300),weapon);
        translate1.setCycleCount(1);
        translate1.setToY(300);
        translate1.setAutoReverse(false);
        translate1.play();
    }

    public void resurrect(){
        TranslateTransition translate1 = new TranslateTransition(Duration.millis(2),weapon);
        translate1.setCycleCount(1);
        translate1.setToY(0);
        translate1.setAutoReverse(false);
        translate1.play();
    }

    public abstract void move(AnchorPane pane);
    public abstract void movewithhero();
    public abstract void stopmovewithhero();
}
