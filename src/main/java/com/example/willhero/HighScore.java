package com.example.willhero;

import java.io.Serializable;
import java.util.ArrayList;

public class HighScore implements Serializable {
    private static final long serialVersionUID = 3L;
    private ArrayList<String> usersname = new ArrayList<>();
    private ArrayList<Integer> highscore = new ArrayList<Integer>();

    public HighScore(){
        for(int i = usersname.size(); i < 5; i++){
            usersname.add("");
            highscore.add(0);
        }
    }
    public void addhighscore(User u) {
        for(int i = 0; i < 5; i++){
            if(usersname.get(i).equals(u.getName())){
                if(highscore.get(i) == u.getCurrentScore())return;
            }
        }
        for(int i = 0; i < 5; i++){
            if(highscore.get(i) < u.getCurrentScore()){
                highscore.set(i, u.getCurrentScore());
                usersname.set(i, u.getName());
                break;
            }
        }
    }

    public void addhighscore(int h, String name) {
        for(int i = 0; i < 5; i++){
            if(usersname.get(i).equals(name)){
                if(highscore.get(i) == h)return;
            }
        }
        for(int i = 0; i < 5; i++){
            if(highscore.get(i) < h){
                highscore.set(i, h);
                usersname.set(i, name);
                break;
            }
        }
    }

    public ArrayList getnames(){
        return usersname;
    }

    public ArrayList gethighscore(){
        return highscore;
    }

    public String print(){
        String s = "";
        for(int i = 0; i < 5; i++){
            s += (i+1)+")  "+usersname.get(i)+"  -  "+highscore.get(i)+"\n";
        }
        return s;
    }

}
