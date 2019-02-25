package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import util.Constants;

// TODO -- Add a 0 button
// TODO -- Add a clear/backspace button
// TODO -- Justify the data? (later)
// TODO -- get the time and print at the bottom of the screen

public class UI extends MouseAdapter {
	
	private Main main;
	
	private int mx, my;
	
	private String data = "";
	
	public UI(Main main) {
		this.main = main;
	}

	public void tick() {
		Point cursor = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(cursor, main);
        mx = cursor.x;
        my = cursor.y;
        
        
	}
	
	/**
	 * This is the listener for the mouse events. Same thing as the <code>mouseOver()</code> method.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		//TODO Finish the coordinate system and methods
		// Events for button 1
		if(mouseOver(mx, my, 205,90,100,100)) {
			
			data += "1";
			// Put code here
		}
		// Events for mouse event 2
		if(mouseOver(mx, my, 305,90,100,100)) {
			
			data += "2";
			// Put code here
		}
		// Events for mouse event 3
		if(mouseOver(mx, my, 405,90,100,100)) {
			
			data += "3";
			// Put code here
		}
		// Events for mouse event 4
		if(mouseOver(mx, my, 205,190,100,100)) {
			
			data += "4";
			// Put code here
		}
		// Events for mouse event 5
		if(mouseOver(mx, my, 305,190,100,100)) {
			
			data += "5";
			// Put code here
		}
		// Events for mouse event 6
		if(mouseOver(mx, my, 405,190,100,100)) {
			
			data += "6";
			// Put code here
		}
		// Events for mouse event 7
		if(mouseOver(mx, my, 205,290,100,100)) {
			
			data += "7";
			// Put code here
		}
		// Events for mouse event 8
		if(mouseOver(mx, my, 305,290,100,100)) {
			
			data += "8";
			// Put code here
		}
		// Events for mouse event 9
		if(mouseOver(mx, my, 405,290,100,100)) {
			
			data += "9";
			// Put code here
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		
		g.setFont(new Font("ariel", Font.BOLD, 45));
		g.drawString(data, 300, 42);
		
		g.drawLine(0, 60, Constants.WIDTH, 60);
		
		g.setFont(new Font("ariel", Font.BOLD, 20));
		int num = 1;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				
				g.drawRect((i * 100) + 205 , (j * 100) + 90, 100, 100);
				g.drawString(num + "", (j * 100) + 245, (i * 100) + 135);
				num++;
			}
		}
	}
	
	/**
	 * Implement this method if you want to activate something while the mouse is OVER something
	 * Implement this with (mx, my, ...) args into the tick method.
	 * @param mx Mouse position in the x direction
	 * @param my Mouse position in the y direction
	 * @param x The starting x
	 * @param y The starting y
	 * @param width The width of the rectangle
	 * @param height The height of the rectangle
	 * @return Boolean whether or not the mouse is over the rectangle defined in the args
	 */
	@SuppressWarnings("unused")
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            return my > y && my < y + height;
        } else {
            return false;
        }
    }
	
}
