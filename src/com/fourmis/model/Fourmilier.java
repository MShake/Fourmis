package com.fourmis.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class Fourmilier extends JPanel implements Predator{
	private double cx; 					// coordonnée en x
	private double cy; 					// coordonnée en y
	private boolean haveFood = false;	// possède de la nourriture
	private int size = 8;				// taille de la fourmi
	private int sens = 0;				// direction (en hexagone)
	private int maxX;					// valeur maximal de la fenêtre en x
	private int maxY;					// valeur maximal de la fenêtre en y
	
	public Fourmilier(double cx, double cy, int maxX, int maxY){
		
		this.cx = cx;
		this.cy = cy;
		this.maxX = maxX;
		this.maxY = maxY;
	
	    this.setPreferredSize(new Dimension(this.size, this.size));
	    
	    this.setOpaque(false);
	}
	
	public void move(ArrayList<Nourriture> nourritures){
		if(!this.haveFood){
			
			//Changement de sens aléatoire ou si elle touche le bord de la fenêtre
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
			
			//Gestion du sens de la fourmi en hexagone
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
			
			//Regarde si la fourmi est sur une source de nourriture
			for(Nourriture n : nourritures){
				int centerXFourmi = (int)cx+(size/2);
				int centerYFourmi = (int)cy+(size/2);
				if(centerXFourmi >= n.getCx() && centerXFourmi <= n.getCx()+n.getWidth() && centerYFourmi >= n.getCy() && centerYFourmi <= n.getCy()+n.getHeight()){
					this.haveFood = true;
					n.setQuantity(n.getQuantity()-1);
					break;
				}
			}
		}
	}
	
	public void draw(Graphics g){
		Color colorFourmilier = new Color(33,72,113);
		g.setColor(colorFourmilier);
		g.fillOval((int)cx, (int)cy, 20, 20);
	
		
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

	public boolean isHaveFood() {
		return haveFood;
	}

	public void setHaveFood(boolean haveFood) {
		this.haveFood = haveFood;
	}
	
	
}

