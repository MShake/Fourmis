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
	
	public boolean collidepoint(int posX, int posY, int width, int height){
		boolean collide = false;
		
		if((posX>=cx && posX<=cx+this.getWidth() && posY>=cy && posY<=cy+this.getHeight()) ||
				(posX+width>=cx && posX+width<=cx+this.getWidth() && posY>=cy && posY<=cy+this.getHeight()) ||
				(posX+width>=cx && posX+width<=cx+this.getWidth() && posY+height>=cy && posY+height<=cy+this.getHeight()) ||
				(posX>=cx && posX<=cx+this.getWidth() && posY+height>=cy && posY+height<=cy+this.getHeight())){
			collide = true;
		}
		
		return collide;
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
