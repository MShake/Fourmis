package com.fourmis.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class Fourmi extends JPanel{
	private int cx; 					// coordonnée en x
	private int cy; 					// coordonnée en y
	private boolean haveFood = false;	// possède de la nourriture
	private int size = 8;				// taille de la fourmi
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
	
	    this.setPreferredSize(new Dimension(this.size, this.size));
	   
	    
	    this.setOpaque(false);
	}
	
	public void move(Fourmiliere fourmiliere, ArrayList<Nourriture> nourritures){
		if(!this.haveFood){
			
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
			
			for(Nourriture n : nourritures){
				int centerXFourmi = cx+(size/2);
				int centerYFourmi = cy+(size/2);
				if(centerXFourmi >= n.getCx() && centerXFourmi <= n.getCx()+n.getWidth() && centerYFourmi >= n.getCy() && centerYFourmi <= n.getCy()+n.getHeight()){
					this.haveFood = true;
					n.setQuantity(n.getQuantity()-1);
					break;
				}
			}
		}else{
			if(cx != positionXFourmiliere || cy != positionYFourmiliere){
				int stepX = 0;
				int stepY = 0;
				if(Math.abs(this.positionYFourmiliere-this.cy) != 0){
					stepX = Math.round(Math.abs(this.positionXFourmiliere-this.cx) / Math.abs(this.positionYFourmiliere-this.cy));
					if(stepX != 0)
						stepX /= stepX;
				}
				if(Math.abs(this.positionXFourmiliere-this.cx) != 0){
					stepY = Math.round(Math.abs(this.positionYFourmiliere-this.cy) / Math.abs(this.positionXFourmiliere-this.cx));
					if(stepY != 0)
						stepY /= stepY;
				}
				
				if(cx < positionXFourmiliere && cy < positionYFourmiliere){
					cx += stepX;
					cy += stepY;
				}else if(cx < positionXFourmiliere && cy >= positionYFourmiliere){
					cx += stepX;
					cy -= stepY;
				}else if(cx >= positionXFourmiliere && cy >= positionYFourmiliere){
					cx -= stepX;
					cy -= stepY;
				}else if(cx >= positionXFourmiliere && cy < positionYFourmiliere){
					cx -= stepX;
					cy += stepY;
				}
				
			}else{
				fourmiliere.setQuantity(fourmiliere.getQuantity()+1);
				haveFood = false;
			}
		}
	}
	
	public void draw(Graphics g){
		g.setColor(Color.black);
		g.fillOval(cx, this.cy, 8, 8);
		if(this.isHaveFood())
			g.setColor(Color.red);
		else
			g.setColor(Color.white);
		g.fillOval(cx+2,  cy+2, 4, 4);
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
