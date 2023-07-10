package com.example.willhero;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public abstract class Gameobject {
    public abstract void display(AnchorPane mainpane);
    public abstract void jump();
    public abstract ImageView getImage();
    public abstract void oncollide(Gameobject g);

    public abstract void remove(AnchorPane mainpane);
}
