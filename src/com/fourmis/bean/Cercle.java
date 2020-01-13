package com.fourmis.bean;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Cercle extends JPanel implements Obstacle {
	private int cx;
	private int cy;
	private int sizeCircle;
	private int centerX;
	private int centerY;
	
	Cercle(int cx, int cy, int size) {
		this.cx = cx;
		this.cy = cy;
		this.sizeCircle = size;
		this.centerX = cx+(size/2);
		this.centerY = cy+(size/2);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillOval(cx, cy, sizeCircle, sizeCircle);
	}
	
	boolean collision(Cercle c) {
		return (c.centerX - this.centerX) * (c.centerX - this.centerX) + (this.centerY - c.centerY) * (this.centerY - c.centerY) <= (this.sizeCircle / 2 + c.sizeCircle / 2) * (this.sizeCircle / 2 + c.sizeCircle / 2);
	}
}
