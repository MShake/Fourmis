package com.fourmis.model;

import java.awt.Color;
import java.awt.Graphics;
import java.math.BigDecimal;
import java.math.MathContext;

import javax.swing.JPanel;

public class Pheromone extends JPanel{
	private double cx; //coordonnée en x
	private double cy; //coordonnée en y
	private int quantity;
	
	public Pheromone(double cx, double cy){
		this.cx = cx;
		this.cy = cy;
		this.quantity = 1100;
	}
	
	public Color getColor(){
		
		BigDecimal result = null;
		BigDecimal plafond = new BigDecimal(1530);
		BigDecimal colorMax = new BigDecimal(255);
		result = (colorMax.multiply(new BigDecimal(this.quantity))).divide(plafond, BigDecimal.ROUND_DOWN);
		if(this.quantity >1530){
			this.quantity = 1530;
			return new Color(255,255,255);
		}
		return new Color(result.intValue(),result.intValue(),result.intValue());
	}
	
	public void draw(Graphics g){
		
		
		g.setColor(this.getColor());
		
		g.fillRect((int)this.getCx(), (int)this.getCy(), 3, 3);
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
