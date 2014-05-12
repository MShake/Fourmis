package com.fourmis.model;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Nourriture extends JPanel {
	
	private int cx;
	private int cy;
	private int quantity;
	
	public Nourriture(int cx, int cy, int quantity) {
		this.cx = cx;
		this.cy = cy;
		this.quantity = quantity*2;
		
		this.setSize(quantity, quantity);
	}
	
	public void draw(Graphics g) {
		g.setColor(new Color(255, 0, 0));
		g.fillRect(cx, cy, this.getWidth(), this.getHeight());
		g.setColor(new Color(100, 0, 0));
		g.drawRect(cx, cy, this.getWidth(), this.getHeight());
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
		this.setSize(quantity/2, quantity/2);
	}

}
