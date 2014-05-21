package com.fourmis.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class Coccinelle extends JPanel implements Predator{
	private double cx; 					// coordonn�e en x
	private double cy; 					// coordonn�e en y
	private boolean haveFood = false;	// poss�de de la nourriture
	private int size = 8;				// taille de la fourmi
	private int sens = 0;				// direction (en hexagone)
	private int maxX;					// valeur maximal de la fen�tre en x
	private int maxY;					// valeur maximal de la fen�tre en y
	
	public Coccinelle(double cx, double cy, int maxX, int maxY){
		
		this.cx = cx;
		this.cy = cy;
		this.maxX = maxX;
		this.maxY = maxY;
	
	    this.setPreferredSize(new Dimension(this.size, this.size));
	    
	    this.setOpaque(false);
	}
	
	public void move(ArrayList<Nourriture> nourritures){	
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
		
		//Gestion du sens de la coccinelle en hexagone
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
		
		//Regarde si la coccinelle est sur une source de nourriture
		for(Nourriture n : nourritures){
			int centerXCocci = (int)cx+(size/2);
			int centerYCocci = (int)cy+(size/2);
			if(centerXCocci >= n.getCx() && centerXCocci <= n.getCx()+n.getWidth() && centerYCocci >= n.getCy() && centerYCocci <= n.getCy()+n.getHeight()){
				if(n.getCx()%2==0 && n.getCy()%2==0)
					n.setQuantity(n.getQuantity()-1);
				break;
			}
		}
	}
	
	public void draw(Graphics g){
		g.setColor(Color.black);
		g.fillOval((int)cx, (int)cy, 20, 20);
	
		g.setColor(Color.red);
		g.fillOval((int)cx+2,  (int)cy+2, 15, 15);
		
		g.setColor(Color.black);
		g.fillOval((int)cx+5,  (int)cy+2, 4, 4);
		g.setColor(Color.black);
		g.fillOval((int)cx+10,  (int)cy+2, 4, 4);
		g.setColor(Color.black);
		g.fillOval((int)cx+3,  (int)cy+7, 4, 4);
		g.setColor(Color.black);
		g.fillOval((int)cx+5,  (int)cy+12, 4, 4);
		g.setColor(Color.black);
		g.fillOval((int)cx+10,  (int)cy+12, 4, 4);
		g.setColor(Color.black);
		g.fillOval((int)cx+12,  (int)cy+7, 4, 4);
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

