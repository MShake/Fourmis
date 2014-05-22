package com.fourmis.bean;

import java.awt.Color;
import java.awt.Graphics;
import java.math.BigDecimal;
import java.math.MathContext;

import javax.swing.JPanel;

public class Pheromone extends JPanel{
	private final int PHEROMONE_SIZE = 3;
	private final int PHEROMONE_COLOR = 25;
	private int cx; //coordonnée en x
	private int cy; //coordonnée en y
	private int quantity;
	
	public Pheromone(int cx, int cy){
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
			return new Color(PHEROMONE_COLOR, PHEROMONE_COLOR, PHEROMONE_COLOR, 255);
		}
		return new Color(PHEROMONE_COLOR,PHEROMONE_COLOR,PHEROMONE_COLOR,result.intValue());
	}
	
	public void draw(Graphics g){
		g.setColor(this.getColor());
		g.fillRect(this.getCx(), this.getCy(), PHEROMONE_SIZE, PHEROMONE_SIZE);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cx;
		result = prime * result + cy;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Pheromone)) {
			return false;
		}
		Pheromone other = (Pheromone) obj;
		if (cx != other.cx) {
			return false;
		}
		if (cy != other.cy) {
			return false;
		}
		return true;
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
	}
	
}
