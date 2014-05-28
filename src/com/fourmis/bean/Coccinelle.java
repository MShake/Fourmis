package com.fourmis.bean;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class Coccinelle extends JPanel implements Predator{
	private int cx; 					// coordonnée en x
	private int cy; 					// coordonnée en y
	private boolean haveFood = false;	// possède de la nourriture
	private int size = 8;				// taille de la fourmi
	private int sens = 0;				// direction
	private int maxX;					// valeur maximal de la fenêtre en x
	private int maxY;					// valeur maximal de la fenêtre en y
	
	public Coccinelle(int cx, int cy, int maxX, int maxY){
		
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
			int centerXCocci = cx+(size/2);
			int centerYCocci = cy+(size/2);
			if(centerXCocci >= n.getCx() && centerXCocci <= n.getCx()+n.getWidth() && centerYCocci >= n.getCy() && centerYCocci <= n.getCy()+n.getHeight()){
				if(n.getCx()%2==0 && n.getCy()%2==0)
					n.setQuantity(n.getQuantity()-1);
				break;
			}
		}
	}
	
	public void draw(Graphics g){
		g.setColor(Color.black);
		g.fillOval(cx, cy, 15, 15);
	
		g.setColor(Color.red);
		g.fillOval(cx+2,  cy+2, 11, 11);
		
		g.setColor(Color.black);
		g.fillOval(cx+6,  cy+2, 3, 3);
		g.setColor(Color.black);
		g.fillOval(cx+3,  cy+5, 3, 3);
		g.setColor(Color.black);
		g.fillOval(cx+4,  cy+9, 3, 3);
		g.setColor(Color.black);
		g.fillOval(cx+8,  cy+9, 3, 3);
		g.setColor(Color.black);
		g.fillOval(cx+9,  cy+5, 3, 3);


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

