package com.example.willhero;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Game extends Application implements Serializable{
    private Stage greeting_stage;

    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        this.greeting_stage = stage;
        stage.setResizable(false);
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);
        stage.setTitle("Will Hero");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("greeting_page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600,Color.rgb(29,200,255,1));
        GameController c = new GameController();
        stage.setScene(scene);
        stage.show();
//
//        Timeline display = new Timeline( new KeyFrame(Duration.millis(2000), new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                try {
//                    c.displaygame(stage);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }));
//        display.play();

    }

    public static void main(String[] args) {
        launch();
    }
}



//    public void generateplatforms(){
//        int c = 100;
//        int r = 0;
//
//        Platform p1 = new Platform(rootmain, 0);
//        platform.add(p1);
//        platform.get(0).setPlatformx(c+r);
//        c += platform.get(0).getPlatform().getFitWidth();
//        r = 50;
//        platform.get(0).display(rootmain);
//
//        for(int i = 1; i< 13; i++){
//            Platform p = new Platform(rootmain);
//            platform.add(p);
//            platform.get(i).setPlatformx(c+r);
//            c += platform.get(i).getPlatform().getFitWidth();
//            r = 50;
//            platform.get(i).display(rootmain);
//            System.out.println(platform.get(i).getPlatform().getFitWidth());
//        }
//    }



//    public void generateorcs(){
//        for(int i = 1; i< platform.size(); i++){
//            if(platform.get(i).getPlatform().getFitWidth() >= 300){
//                for(int j = 0; j < Math.random()*platform.get(i).getPlatform().getFitWidth()/200 +1; j++){
//                    Orc o = new Orc();
//                    orcs.add(o);
//                }
//            }
//            Orc o = new Orc();
//            orcs.add(o);
//
//        }
//    }



//           moveorcs.get(moveorcs.size()-1).setOnFinished(e->
//           {
//               double temp = -hero.getHero().getX() + 320;
//               for (int i = 0; i < moveplatformsback.size(); i++) {
//                   moveplatformsback.remove(i);
//               }
//               for (int i = 0; i < moveorcsback.size(); i++) {
//                   moveorcsback.remove(i);
//               }
//               for (int i = 0; i < platform.size(); i++) {
//                   moveplatformsback.add(Animations.translateTransition(platform.get(i).getPlatform(), 3000, temp, 0, false, 1));
//               }
//               for (int i = 0; i < orcs.size(); i++) {
//                   moveorcsback.add(Animations.translateTransition(orcs.get(i).getOrc(), 3000, temp, 0, false, 1));
//               }
//               for (int i = 0; i < moveplatformsback.size(); i++) {
//                   moveplatformsback.get(i).play();
//               }
//               for (int i = 0; i < moveorcsback.size(); i++) {
//                   moveorcsback.get(i).play();
//               }
//           });



//for (int i = 0; i < moveplatformsback.size(); i++) {
//        moveplatformsback.get(i).stop();
//        }
//        for (int i = 0; i < moveorcsback.size(); i++) {
//        moveorcsback.get(i).stop();
//        }

//
//coll.stop();
//        hero.setAnother_space();
//        System.out.println("was in space");
////           Thread thread1 = new Thread() {
////               @Override
////               public void run() {
////
////               }
////           };
//        hero.moveforward();
//
//        Thread thread2 = new Thread(){
//@Override
//public void run(){
////                   coll.start();
////                   if(falg_col) {
////                   hero.getHero().getBoundsInParent().intersects(platform.get(0).getPlatform().getBoundsInParent());
////                   }
//        }
//        };
//
//        Thread thread3 = new Thread(){
//@Override
//public void run(){
//        for(int i = 0; i< moveplatforms.size(); i++){
//        moveplatforms.get(i).play();
//        }
//        for(int i = 0; i< moveorcs.size(); i++){
//        moveorcs.get(i).play();
//        }
//        coll.start();
////                for(int i = 0; i< 13; i++){
////                    moveplatforms.add(Animations.translateTransition(platform.get(i).getPlatform(), 300, -150, 0, false, 1));
////                }
////                for(int i = 0; i< 13; i++){
////                    moveplatforms.get(i).play();
////                }
////                for(int i = 0; i< orcs.size(); i++){
////                    moveorcsback.add(Animations.translateTransition(orcs.get(i).getOrc(), 300, -150, 0, false, 1));
////                }
////                for(int i = 0; i< orcs.size(); i++){
////                    moveorcs.get(i).play();
////                }
//        }
//        };
////
////       Thread thread4 = new Thread(){
////           @Override
////           public void run(){
////
////           }
////       };
//
//        double temp = hero.getHero().getX() - 320;
//
//
//        for(int i = 0; i< platform.size(); i++){
//        moveplatforms.get(i).play();
//        }
//        for(int i = 0; i< orcs.size(); i++){
//        moveorcs.get(i).play();
//        }
//
////           thread1.start();
//        thread3.start();
//        thread2.start();
////           thread4.start





























//    AnimationTimer collplatform  = new AnimationTimer() {
//        @Override
//        public void handle(long l) {
//            boolean flagcool = false;
//            for(int i = 0; i< platform.size(); i++){
//                if(hero.getBoundsInParent().intersects(platform.get(i).getPlatform().getBoundsInParent()) == true ){
//                    flagonplatform = true;
//                    herojump.play();
//                    System.out.println("in here");
//                    return;
//                }
//            }
//            flagexit = true;
//            TranslateTransition translate1 = new TranslateTransition(Duration.millis(300),hero);
//            translate1.setCycleCount(1);
//            translate1.setToY(300);
//            translate1.setAutoReverse(false);
//            translate1.play();
//        }
//    };


//    public void setPlatform(ArrayList p){
//        this.platform = p;
//        System.out.println(platform);
//    }