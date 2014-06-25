package com.fourmis.bean;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.HashSet;

import javax.swing.JPanel;

public class Fourmiliere extends JPanel{
	private int cx; //coordonnée en x
	private int cy; //coordonnée en y
	private int quantity;
	private HashSet<Fourmi> fourmis;
	
	public Fourmiliere(int cx, int cy, int width, int height){
		this.cx = cx;
		this.cy = cy;
		this.quantity = 0;
		this.fourmis = new HashSet<Fourmi>();
		this.setSize(width, height);
	}

	public void draw(Graphics g) {
		g.setColor(new Color(156, 93, 82));
		g.fill3DRect(this.getCx(), this.getCy(), this.getWidth(), this.getHeight(), true);
		g.fill3DRect(this.getCx()+2, this.getCy()+2, this.getWidth()-4, this.getHeight()-4, true);
		g.fill3DRect(this.getCx()+4, this.getCy()+4, this.getWidth()-8, this.getHeight()-8, true);
		g.fill3DRect(this.getCx()+6, this.getCy()+6, this.getWidth()-12, this.getHeight()-12, true);
		g.fill3DRect(this.getCx()+8, this.getCy()+8, this.getWidth()-16, this.getHeight()-16, true);
		g.fill3DRect(this.getCx()+10, this.getCy()+10, this.getWidth()-20, this.getHeight()-20, true);
		g.fill3DRect(this.getCx()+12, this.getCy()+12, this.getWidth()-24, this.getHeight()-24, true);
		g.setColor(new Color(27, 27, 27));
		g.fillOval(this.getCx()+14, this.getCy()+14, this.getWidth()-28, this.getHeight()-28);
	}
	
	public boolean collisionRect(Rectangle r2){
		Rectangle r1 = new Rectangle(cx, cy, this.getWidth(), this.getHeight());
		
		return r1.intersects(r2);
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

	public HashSet<Fourmi> getFourmis() {
		return fourmis;
	}

	public void setFourmis(HashSet<Fourmi> fourmis) {
		this.fourmis = fourmis;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
