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
	
	public Cercle(int cx, int cy, int size) {
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
	
	public boolean collision(Cercle c){
		int d2 = (this.centerX-c.centerX)*(this.centerX-c.centerX) + (this.centerY-c.centerY)*(this.centerY-c.centerY);
		if(d2 > (this.sizeCircle/2 + c.sizeCircle/2)*(this.sizeCircle/2 + c.sizeCircle/2)){
			return false;
		}else{
			return true;
		}
	}

	public int getCx() {
		return cx;
	}

	public void setCx(int cx) {
		this.cx = cx;
	}

	public int getCy() {
		return cy;
	}

	public void setCy(int cy) {
		this.cy = cy;
	}

	public int getSizeCircle() {
		return sizeCircle;
	}

	public void setSizeCircle(int sizeCircle) {
		this.sizeCircle = sizeCircle;
	}

	public int getCenterX() {
		return centerX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}
	
}
