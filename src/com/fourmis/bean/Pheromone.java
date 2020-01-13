package com.fourmis.bean;

import java.awt.Color;
import java.awt.Graphics;
import java.math.BigDecimal;
import java.util.Objects;

import javax.swing.JPanel;

public class Pheromone extends JPanel{
	private static final int PHEROMONE_SIZE = 3;
	private static final int PHEROMONE_COLOR = 25;
	private int cx;
	private int cy;
	private int quantity;
	
	Pheromone(int cx, int cy){
		this.cx = cx;
		this.cy = cy;
		this.quantity = 1100;
	}
	
	private Color getColor(){

		BigDecimal plafond = new BigDecimal(1530);
		BigDecimal colorMax = new BigDecimal(255);
		BigDecimal result = (colorMax.multiply(new BigDecimal(this.quantity))).divide(plafond, BigDecimal.ROUND_DOWN);
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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Pheromone pheromone = (Pheromone) o;
		return cx == pheromone.cx &&
				cy == pheromone.cy &&
				quantity == pheromone.quantity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cx, cy, quantity);
	}

	int getCx() {
		return cx;
	}

	void setCx(int cx) {
		this.cx = cx;
	}

	int getCy() {
		return cy;
	}

	void setCy(int cy) {
		this.cy = cy;
	}

	int getQuantity() {
		return quantity;
	}

	void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
