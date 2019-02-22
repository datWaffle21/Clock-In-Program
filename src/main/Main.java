package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import util.Constants;
import util.Window;

public class Main extends Canvas implements Runnable {
	private static final long serialVersionUID = 3650246170018746785L;
	
	private Thread thread;
	private boolean running = false;

	public Main() {
		new Window(Constants.WIDTH, Constants.HEIGHT, "Clock In Here", this);
	}
	
	public void tick() {
		
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
				
		g.dispose();
		bs.show();
	}
	
	@Override
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now  = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			
			if(running) {
				render();
				frames++;
			}
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
		
	public void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Main();
	}
	
}
