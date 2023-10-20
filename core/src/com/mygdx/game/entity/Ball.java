package com.mygdx.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import java.awt.*;

public class Ball {
    int x;
    int y;
    int size;
    int xSpeed;
    int ySpeed;

    Color color = Color.WHITE;

    public Ball(int x, int y, int size, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public void update() {
        this.x += xSpeed;
        this.y += ySpeed;

        if(this.getLeftBoundary() < 0 || this.getRightBoundary() > Gdx.graphics.getWidth()) {
            this.xSpeed = -this.xSpeed;
        }

        if(this.getBottomBoundary() < 0 || this.getTopBoundary() > Gdx.graphics.getHeight()) {
            this.ySpeed = -this.ySpeed;
        }
    }

    public int getLeftBoundary() {
        return x - size;
    }

    public int getRightBoundary() {
        return x + size;
    }

    public int getTopBoundary() {
        return y + size;
    }

    public int getBottomBoundary() {
        return y - size;
    }
    public void checkCollision(RectangleShape paddle) {
        if(this.collideWith(paddle)) {
            this.ySpeed = -this.ySpeed;
            if(paddle instanceof Brick) {
                Brick brick = (Brick) paddle;
                brick.setDestroyed(true);
            }
        }
    }

    private boolean collideWith(RectangleShape paddle) {
        return checkBottomBoundary(paddle) && checkTopBoundary(paddle) && checkLeftBoundary(paddle) && checkRightBoundary(paddle);
    }

    private boolean checkBottomBoundary(RectangleShape paddle) {
        return this.getBottomBoundary() <= paddle.getTopBoundary() ;
    }

    private boolean checkTopBoundary(RectangleShape paddle) {
        return this.getTopBoundary() >= paddle.getBottomBoundary();
    }

    private boolean checkLeftBoundary(RectangleShape paddle) {
        return this.getLeftBoundary() <= paddle.getRightBoundary();
    }

    private boolean checkRightBoundary(RectangleShape paddle) {
        return this.getRightBoundary() >= paddle.getLeftBoundary();
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(this.color);
        shapeRenderer.circle(this.x, this.y, this.size);
    }
}
