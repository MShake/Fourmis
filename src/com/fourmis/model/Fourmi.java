package com.fourmis.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

public class Fourmi extends JPanel{
	private int cx; 					// coordonnée en x
	private int cy; 					// coordonnée en y
	private boolean haveFood = false;	// possède de la nourriture
	private int sens = 0;				// direction (en hexagone)
	private int maxX;					// valeur maximal de la fenêtre en x
	private int maxY;					// valeur maximal de la fenêtre en y
	private int positionXFourmiliere;	// position en x de la fourmilère
	private int positionYFourmiliere;	// position en y de la fourmilère
	
	public Fourmi(int cx, int cy, int maxX, int maxY){
		
		this.cx = cx;
		this.cy = cy;
		this.positionXFourmiliere = cx;
		this.positionYFourmiliere = cy;
		this.maxX = maxX;
		this.maxY = maxY;
	
	    this.setPreferredSize(new Dimension(8, 8));
	   
	    
	    this.setOpaque(false);
	}
	
	public void move(){
		boolean changeSens = false;
		if(Math.random() < 0.02 || cx == 0 || cy == 0 || cx == maxX || cy == maxY || sens == 0){
			changeSens = true;
		}
		
		if(changeSens){
			Random rand = new Random();
			int newSens = this.sens;
			do{
				newSens = rand.nextInt(6 - 1+1) + 1;
			}while(newSens == this.sens);
			this.sens = newSens;
		}
		
		if(this.sens == 1 && cy > 0){
			cy--;
		}
		else if(this.sens == 2 && cx < maxX && cy > 0){
			cy--;
			cx++;
		}
		else if(this.sens == 3 && cx < maxX && cy < maxY){
			cx++;
			cy++;
		}
		else if(this.sens == 4 && cy < maxY){
			cy++;
		}
		else if(this.sens == 5 && cx > 0 && cy < maxY){
			cx--;
			cy++;
		}
		else if(this.sens == 6 && cx > 0 && cy > 0){
			cx--;
			cy--;
		}
		
		this.setCx(cx);
		this.setCy(cy);
	}
	
	public void draw(Graphics g){
		g.setColor(Color.black);
		g.fillOval(this.getCx(), this.getCy(), 8, 8);
		if(this.isHaveFood())
			g.setColor(Color.red);
		else
			g.setColor(Color.white);
		g.fillOval(this.getCx()+2,  this.getCy()+2, 4, 4);
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

	public boolean isHaveFood() {
		return haveFood;
	}

	public void setHaveFood(boolean haveFood) {
		this.haveFood = haveFood;
	}
	
	
}
