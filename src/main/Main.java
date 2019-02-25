package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import util.Constants;
import util.Window;

public class Main extends Canvas implements Runnable {
	private static final long serialVersionUID = 3650246170018746785L; // Warning if not here so it is :p
		
	private Thread thread; // java stuff i guess. 
	private boolean running = false; // keeps track of whether or not the thread is running
	
	private UI ui;

	public Main() {
		ui = new UI(this);
		
		this.addMouseListener(ui);
		
		new Window(Constants.WIDTH, Constants.HEIGHT, "Clock In Here", this); // creates a new window for the program
		
	}
	
	public void tick() {
		ui.tick();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return; // kinda of a cool command. This skips the rest of the method and resumes the flow as if the rest of the code was executed
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black); // sets the color to black
		g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT); // fills a black rectangle starting at (0,0) and ending at the (width,height) point
				
		ui.render(g);
		
		g.dispose(); // java thing
		bs.show(); // java thing
	}
	
	@Override
	public void run() { // This is kinda hard to explain just ask me about it if you have questions
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
		thread = new Thread(this); // starts a new thread with this as the target (This classes run method is called)
		thread.start(); // Starts the thread
		running = true; // sets running = true
	}
	
	public void stop() {
		try {
			thread.join(); // waits for the thread to stop
			running = false; // sets running to false
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Main(); // constructor for main
	}
	
}
