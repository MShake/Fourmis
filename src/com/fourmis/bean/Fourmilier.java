package com.fourmis.bean;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JPanel;

public class Fourmilier extends JPanel implements Predator{
	private int cx; 					// coordonnée en x
	private int cy; 					// coordonnée en y
	private boolean haveFood = false;	// possède de la nourriture
	private int size = 8;				// taille du fourmilier
	private int sens = 0;				// direction
	private int maxX;					// valeur maximal de la fenêtre en x
	private int maxY;					// valeur maximal de la fenêtre en y
	
	Fourmilier(int cx, int cy, int maxX, int maxY){
		
		this.cx = cx;
		this.cy = cy;
		this.maxX = maxX;
		this.maxY = maxY;
	
	    this.setPreferredSize(new Dimension(this.size, this.size));
	    
	    this.setOpaque(false);
	}
	
	void move(HashSet<Fourmi> fourmis){
		//Changement de sens aléatoire ou si elle touche le bord de la fenêtre
		boolean changeSens = false;
		if(Math.random() < 0.02 || cx == 0 || cy == 0 || cx == maxX || cy == maxY || sens == 0){
			changeSens = true;
		}
		
		if(changeSens){
			Random rand = new Random();
			int newSens;
			do{
				newSens = rand.nextInt(8 - 1+1) + 1;
			}while(newSens == this.sens);
			this.sens = newSens;
		}

		fourmis.removeIf(f -> f.collisionRect(new Rectangle(cx, cy, size, size)));
		
		//Gestion du sens du fourmilier
		if(this.sens == 1 && cy > 0){
			cy--;
		}else if(this.sens == 2 && cx < maxX && cy > 0){
			cy--;
			cx++;
		}else if(this.sens == 3 && cx < maxX){
			cx++;
		}else if(this.sens == 4 && cx < maxX && cy < maxY){
			cx++;
			cy++;
		}else if(this.sens == 5 && cy < maxY){
			cy++;
		}else if(this.sens == 6 && cx > 0 && cy < maxY){
			cx--;
			cy++;
		}else if(this.sens == 7 && cx > 0){
			cx--;
		}else if(this.sens == 8 && cx > 0 && cy > 0){
			cx--;
			cy--;
		}
	}
	
	public void draw(Graphics g){
		Color colorFourmilier = new Color(13,22,90);
		g.setColor(colorFourmilier);
		g.fillOval(cx, cy, 20, 20);
		colorFourmilier = new Color(33,72,113);
		g.setColor(colorFourmilier);
		g.fillOval(cx+2, cy+2, 15, 15);
		colorFourmilier = new Color(53,92,133);
		g.setColor(colorFourmilier);
		g.fillOval(cx+4, cy+4, 10, 10);
	}
}

