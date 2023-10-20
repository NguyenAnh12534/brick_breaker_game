package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import javax.swing.*;
import java.awt.*;

public class Paddle extends RectangleShape{
    public Paddle(int x, int y, int width, int height){
        super(x, y, width, height);
    }

    public void update() {
        this.x = Gdx.input.getX();
        this.y = Gdx.graphics.getHeight() -  Gdx.input.getY();
    }



    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(this.x, this.y, this.width, this.height);
    }
}
