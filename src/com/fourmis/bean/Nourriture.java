package com.fourmis.bean;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class Nourriture extends JPanel {
	
	private int cx;
	private int cy;
	private int quantity;
	private double step;
	private int opacity;
	
	Nourriture(int cx, int cy, int quantity) {
		this.cx = cx;
		this.cy = cy;
		this.quantity = quantity*2;
		this.opacity = 255;
		this.step = 255 / this.quantity;
		this.setSize(quantity, quantity);
	}
	
	public void draw(Graphics g) {
		g.setColor(new Color(255, 0, 0, opacity));
		g.fill3DRect(cx, cy, this.getWidth(), this.getHeight(), true);
		g.setColor(new Color(100, 0, 0, opacity));
		g.draw3DRect(cx, cy, this.getWidth(), this.getHeight(), true);
	}
	
	boolean collisionRect(Rectangle r2){
		Rectangle r1 = new Rectangle(cx, cy, this.getWidth(), this.getHeight());
		
		return r1.intersects(r2);
	}

	int getCx() {
		return cx;
	}

	int getCy() {
		return cy;
	}

	int getQuantity() {
		return quantity;
	}

	void setQuantity(int quantity) {
		this.quantity = quantity;
		this.opacity -= step;
	}

}
