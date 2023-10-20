package com.mygdx.game.entity;

public class RectangleShape {
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    public RectangleShape(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public int getLeftBoundary() {
        return x;
    }

    public int getRightBoundary() {
        return x + width;
    }

    public int getTopBoundary() {
        return y + height;
    }

    public int getBottomBoundary() {
        return y;
    }
}
