package com.fourmis.model;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Fourmiliere extends JPanel{
	private int cx; //coordonnée en x
	private int cy; //coordonnée en y
	
	public Fourmiliere(int cx, int cy, int width, int height){
		this.cx = cx;
		this.cy = cy;
		this.setSize(width, height);
	}

	public void draw(Graphics g) {
		g.setColor(new Color(156, 93, 82));
		g.fillRect(this.getCx(), this.getCy(), this.getWidth(), this.getHeight());
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
	
}
