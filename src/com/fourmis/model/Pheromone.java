package com.fourmis.model;

import java.awt.Color;
import java.awt.Graphics;

public class Pheromone {
	private double cx; //coordonnée en x
	private double cy; //coordonnée en y
	private int quantity;
	
	public Pheromone(double cx, double cy){
		this.cx = cx;
		this.cy = cy;
		this.quantity = 1100;
	}
	
	public void draw(Graphics g){
		
		if(this.quantity>1000){
			g.setColor(new Color(50,50,50));
		}
		else if(this.quantity>900){
			g.setColor(new Color(100,100,100));
		}
		else if(this.quantity>800){
			g.setColor(new Color(110,110,110));
		}
		else if(this.quantity>700){
			g.setColor(new Color(120,120,120));
		}
		else if(this.quantity>600){
			g.setColor(new Color(130,130,130));
		}
		else if(this.quantity>500){
			g.setColor(new Color(140,140,140));
		}
		else if(this.quantity>400){
			g.setColor(new Color(150,150,150));
		}
		else if(this.quantity>300){
			g.setColor(new Color(160,160,160));
		}
		else if(this.quantity>200){
			g.setColor(new Color(170,170,170));
		}
		else if(this.quantity>100){
			g.setColor(new Color(180,180,180));
		}
		else
			g.setColor(new Color(200,200,200));
		
		g.fillRect((int)this.getCx(), (int)this.getCy(), 5, 5);
	}

	public double getCx() {
		return cx;
	}

	public void setCx(double cx) {
		this.cx = cx;
	}

	public double getCy() {
		return cy;
	}

	public void setCy(double cy) {
		this.cy = cy;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
