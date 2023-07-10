package com.example.willhero;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int currentScore;
    private int highScore;
    private int coincollected;

    User(String name) {
        this.name=name;
        this.currentScore=0;
        this.highScore=0;
        coincollected = 0;

    }
    public void setCurrentScore(int currentscore) {
        this.currentScore = currentscore;
        highScore=Math.max(highScore,currentscore);
    }

    public void setCoincollected(int x){
        coincollected += x;
    }

    public String getName() {return this.name;}
    public int getCoincollected() {
        return coincollected;
    }

    public int getHighScore() {
        return highScore;
    }

    public int getCurrentScore() {
        return currentScore;
    }


}
