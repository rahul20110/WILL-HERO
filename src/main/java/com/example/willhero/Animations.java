package com.example.willhero;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Animations {

    public static TranslateTransition translateTransition(Node n, double time, double distanceX, double distanceY, boolean repeat, int cycle){
        TranslateTransition translate = new TranslateTransition(Duration.millis(time),n);
        if(cycle == -1){
            translate.setCycleCount(TranslateTransition.INDEFINITE);
        }
        else{
            translate.setCycleCount(cycle);
        }
        translate.setByX(distanceX);
        translate.setByY(distanceY);
        translate.setAutoReverse(repeat);
        return translate;
    }

    public static TranslateTransition translateTransitionmoveto(Node n, double time, double distanceXs ,double distanceXe, boolean repeat, int cycle){
        TranslateTransition translate = new TranslateTransition(Duration.millis(time),n);
        if(cycle == -1){
            translate.setCycleCount(TranslateTransition.INDEFINITE);
        }
        else{
            translate.setCycleCount(cycle);
        }
        translate.setToX(distanceXe);
        translate.setFromX(distanceXs);
        translate.setAutoReverse(repeat);
        return translate;
    }

    public static TranslateTransition translateTransitionmovetoY(Node n, double time, double distanceYs ,double distanceYe, boolean repeat, int cycle){
        TranslateTransition translate = new TranslateTransition(Duration.millis(time),n);
        if(cycle == -1){
            translate.setCycleCount(TranslateTransition.INDEFINITE);
        }
        else{
            translate.setCycleCount(cycle);
        }
        translate.setToY(distanceYe);
        translate.setFromY(distanceYs);
        translate.setAutoReverse(repeat);
        return translate;
    }

}
