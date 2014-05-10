package com.fourmis.model;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Nourriture extends JPanel {
	
	private int cx;
	private int cy;
	private int quantity;
	
	public Nourriture(int cx, int cy, int quantity) {
		this.cx = cx;
		this.cy = cy;
		this.quantity = quantity;
		
		this.setSize(quantity, quantity);
	}
	
	public void draw(Graphics g) {
		g.setColor(new Color(255, 0, 0));
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
