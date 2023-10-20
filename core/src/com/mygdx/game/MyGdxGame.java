package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.entity.Ball;
import com.mygdx.game.entity.Brick;
import com.mygdx.game.entity.Paddle;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {
	private static final int BRICK_HEIGHT = 20;
	private static final int BRICK_WIDTH = 63;

	ShapeRenderer shapeRenderer;
	int x = 50;
	int xSpeed = 10;
	int y = 50;
	List<Ball> balls = new ArrayList<Ball>();
	List<Brick> bricks = new ArrayList<Brick> ();
	Paddle paddle;
	Random random = new Random();
	@Override
	public void create() {
		shapeRenderer = new ShapeRenderer();
		paddle = new Paddle(10, 10, 200, 5);
		for(int i = 0; i < 2; i++) {
			this.balls.add(new Ball(random.nextInt(Gdx.graphics.getWidth()),
					random.nextInt(Gdx.graphics.getHeight()),
					random.nextInt(100), random.nextInt(15), random.nextInt(15)));
		}

		for(int y = Gdx.graphics.getHeight() / 2; y < Gdx.graphics.getHeight(); y += BRICK_HEIGHT + 10){
			for(int x = 0; x < Gdx.graphics.getWidth(); x += BRICK_WIDTH +10) {
				this.bricks.add(
						new Brick(x, y, BRICK_WIDTH, BRICK_HEIGHT)
				);
			}
		}
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.input.getX();

		for(Ball ball : this.balls) {
			ball.checkCollision(this.paddle);
			ball.update();
			shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
			ball.draw(shapeRenderer);
			shapeRenderer.end();
		}

		for(Brick brick : this.bricks) {
			shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
			brick.draw(shapeRenderer);
			shapeRenderer.end();
		}

		for(Ball ball : this.balls) {
			for(Brick brick : this.bricks) {
				ball.checkCollision(brick);
			}
		}

		Iterator<Brick> brickIterator = this.bricks.listIterator();
		while(brickIterator.hasNext()) {
			Brick brick = brickIterator.next();
			if(brick.isDestroyed()){
				brickIterator.remove();
			}
		}

		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.BROWN);
		this.paddle.update();
		this.paddle.draw(this.shapeRenderer);
		shapeRenderer.end();
	}
}
