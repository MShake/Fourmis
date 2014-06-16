package com.fourmis.bean;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JPanel;

public class Fourmi extends JPanel{
	
	private int id;
	private final double CHANGE_DIR = 0.005;
	private final double CHANGE_DIR_RETURN = 0.03;
	private int cx; 					// coordonnée en x
	private int cy; 					// coordonnée en y
	private boolean haveFood = false;	// possède de la nourriture
	private int size = 8;				// taille de la fourmi
	private int sens = 0;				// direction
	private int maxX;					// valeur maximal de la fenêtre en x
	private int maxY;					// valeur maximal de la fenêtre en y
	private int directionX = 0;
	private int directionY = 0;
	private boolean drawBody;
	
	public Fourmi(int cx, int cy, int maxX, int maxY){
		
		this.cx = cx;
		this.cy = cy;
		this.maxX = maxX;
		this.maxY = maxY;
		
		this.setSize(size, size);
	    this.setPreferredSize(new Dimension(this.size, this.size));
	    
	    this.setOpaque(false);
	}
	
	public void move(Fourmiliere fourmiliere, ArrayList<Nourriture> nourritures, HashSet<Pheromone> pheromones){
		if(!this.haveFood){
			boolean findPheromone = false;
			if(pheromones.contains(new Pheromone(cx+size/2, cy+size/2)) && !(cx == fourmiliere.getCx()+fourmiliere.getWidth()/2-size/2 && cy == fourmiliere.getCy()+fourmiliere.getHeight()/2-size/2)){
				findPheromone = true;
			}
			
			if(findPheromone){
				double distance = 0;
				directionX = 0; 
				directionY = 0;
				Pheromone p = new Pheromone(cx+size/2, cy+size/2);
				int centerXFourmiliere = fourmiliere.getCx()+fourmiliere.getWidth()/2-size/2;
				int centerYFourmiliere = fourmiliere.getCy()+fourmiliere.getHeight()/2-size/2;
				
				if(pheromones.contains(p) && distance(p.getCx(), p.getCy()-1, centerXFourmiliere, centerYFourmiliere) > distance){
					distance = distance(p.getCx(), p.getCy()-1, centerXFourmiliere, centerYFourmiliere);
					directionX = 0;
					directionY = -1;
				}
				if(pheromones.contains(p) && distance(p.getCx()+1, p.getCy()-1, centerXFourmiliere, centerYFourmiliere) > distance){
					distance = distance(p.getCx()+1, p.getCy()-1, centerXFourmiliere, centerYFourmiliere);
					directionX = 1;
					directionY = -1;
				}
				if(pheromones.contains(p) && distance(p.getCx()+1, p.getCy(), centerXFourmiliere, centerYFourmiliere) > distance){
					distance = distance(p.getCx()+1, p.getCy(), centerXFourmiliere, centerYFourmiliere);
					directionX = 1;
					directionY = 0;
				}
				if(pheromones.contains(p) && distance(p.getCx()+1, p.getCy()+1, centerXFourmiliere, centerYFourmiliere) > distance){
					distance = distance(p.getCx()+1, p.getCy()+1, centerXFourmiliere, centerYFourmiliere);
					directionX = 1;
					directionY = 1;
				}
				if(pheromones.contains(p) && distance(p.getCx(), p.getCy()+1, centerXFourmiliere, centerYFourmiliere) > distance){
					distance = distance(p.getCx(), p.getCy()+1, centerXFourmiliere, centerYFourmiliere);
					directionX = 0;
					directionY = 1;
				}
				if(pheromones.contains(p) && distance(p.getCx()-1, p.getCy()+1, centerXFourmiliere, centerYFourmiliere) > distance){
					distance = distance(p.getCx()-1, p.getCy()+1, centerXFourmiliere, centerYFourmiliere);
					directionX = -1;
					directionY = 1;
				}
				if(pheromones.contains(p) && distance(p.getCx()-1, p.getCy(), centerXFourmiliere, centerYFourmiliere) > distance){
					distance = distance(p.getCx()-1, p.getCy(), centerXFourmiliere, centerYFourmiliere);
					directionX = 1;
					directionY = 0;
				}
				if(pheromones.contains(p) && distance(p.getCx()-1, p.getCy()-1, centerXFourmiliere, centerYFourmiliere) > distance){
					distance = distance(p.getCx()-1, p.getCy()-1, centerXFourmiliere, centerYFourmiliere);
					directionX =-1;
					directionY = -1;
				}
				
				if(directionX >= 0)
					cx += directionX;
				else
					cx -= Math.abs(directionX);
				
				if(directionY >= 0)
					cy += directionY;
				else
					cy -= Math.abs(directionY);
			}else{
				//Changement de sens aléatoire ou si elle touche le bord de la fenêtre
				boolean changeSens = false;
				directionX = 0;
				directionY = 0;
				if(Math.random() < CHANGE_DIR || cx == 0 || cy == 0 || cx == maxX || cy == maxY || sens == 0){
					changeSens = true;
				}
				
				if(changeSens){
					Random rand = new Random();
					int newSens = this.sens;
					do{
						newSens = rand.nextInt(8 - 1+1) + 1;
					}while(newSens == this.sens);
					this.sens = newSens;
				}
				
				//Gestion du sens de la fourmi
				if(this.sens == 1 && cy > 0){
					directionX = 0;
					directionY = -1;
				}
				else if(this.sens == 2 && cx < maxX && cy > 0){
					directionX = 1;
					directionY = -1;
				}
				else if(this.sens == 3 && cx < maxX && cy < maxY){
					directionX = 1;
					directionY = 1;
				}
				else if(this.sens == 4 && cx < maxX){
					directionX = 1;
					directionY = 0;
				}
				else if(this.sens == 5 && cy < maxY){
					directionX = 0;
					directionY = 1;
				}
				else if(this.sens == 6 && cx > 0 && cy < maxY){
					directionX = -1;
					directionY = 1;
				}
				else if(this.sens == 7 && cx > 0){
					directionX = -1;
					directionY = 0;
				}
				else if(this.sens == 8 && cx > 0 && cy > 0){
					directionX = -1;
					directionY = -1;
				}
				
				if(directionX >= 0)
					cx += directionX;
				else
					cx -= Math.abs(directionX);
				
				if(directionY >= 0)
					cy += directionY;
				else
					cy -= Math.abs(directionY);
			}
			
			//Regarde si la fourmi est sur une source de nourriture
			for(Nourriture n : nourritures){
				int centerXFourmi = cx+(size/2);
				int centerYFourmi = cy+(size/2);
				if(centerXFourmi == n.getCx()+n.getWidth()/2 && centerYFourmi == n.getCy()+n.getHeight()/2){
					this.haveFood = true;
					n.setQuantity(n.getQuantity()-1);
					break;
				}
			}
		}else{
			//Gestion du mouvement de la fourmi dans le cas où elle a de la nourriture
			directionX = 0;
			directionY = 0;
			int centerXFourmiliere = fourmiliere.getCx()+fourmiliere.getWidth()/2-size/2;
			int centerYFourmiliere = fourmiliere.getCy()+fourmiliere.getHeight()/2-size/2;
			int centerXFourmi = cx+size/2;
			int centerYFourmi = cy+size/2;
			if(pheromones.contains(new Pheromone(centerXFourmi, centerYFourmi))){
				Pheromone p = getPheromoneByCoord(centerXFourmi, centerYFourmi, pheromones);
				p.setQuantity(p.getQuantity()+100);
			}
			else{
				pheromones.add(new Pheromone(centerXFourmi, centerYFourmi));
			}
			
			boolean changeSens = false;
			if(Math.random() < CHANGE_DIR_RETURN || sens == 0){
				changeSens = true;
			}
			
			if(changeSens){
				Random rand = new Random();
				this.sens = rand.nextInt(8 - 1+1) + 1;
			}
			
			if(cx != centerXFourmiliere || cy != centerYFourmiliere){
				double minDistance = Double.MAX_VALUE;
				if((distance(cx, cy-1, centerXFourmiliere, centerYFourmiliere) < minDistance && cy > 0) || (changeSens && sens == 1)){
					directionX = 0;
					directionY = -1;
				}
				if((distance(cx+1, cy-1, centerXFourmiliere, centerYFourmiliere) < minDistance && cx < maxX && cy > 0) || (changeSens && sens == 2)){
					minDistance = distance(cx+1, cy-1, centerXFourmiliere, centerYFourmiliere);
					directionX = 1;
					directionY = -1;
				}
				if((distance(cx+1, cy, centerXFourmiliere, centerYFourmiliere) < minDistance && cx < maxX) || (changeSens && sens == 3)){
					minDistance = distance(cx+1, cy, centerXFourmiliere, centerYFourmiliere);
					directionX = 1;
					directionY = 0;
				}
				if((distance(cx+1, cy+1, centerXFourmiliere, centerYFourmiliere) < minDistance && cx < maxX && cy < maxY) || (changeSens && sens == 4)){
					minDistance = distance(cx+1, cy+1, centerXFourmiliere, centerYFourmiliere);
					directionX = 1;
					directionY = 1;
				}
				if((distance(cx, cy+1, centerXFourmiliere, centerYFourmiliere) < minDistance && cy < maxY) || (changeSens && sens == 5)){
					minDistance = distance(cx, cy+1, centerXFourmiliere, centerYFourmiliere);
					directionX = 0;
					directionY = 1;
				}
				if((distance(cx-1, cy+1, centerXFourmiliere, centerYFourmiliere) < minDistance && cx > 0 && cy < maxY) || (changeSens && sens == 6)){
					minDistance = distance(cx-1, cy+1, centerXFourmiliere, centerYFourmiliere);
					directionX = -1;
					directionY = 1;
				}
				if((distance(cx-1, cy, centerXFourmiliere, centerYFourmiliere) < minDistance && cx > 0) || (changeSens && sens == 7)){
					minDistance = distance(cx-1, cy, centerXFourmiliere, centerYFourmiliere);
					directionX = -1;
					directionY = 0;
				}
				if((distance(cx-1, cy-1, centerXFourmiliere, centerYFourmiliere) < minDistance && cx > 0 && cy > 0) || (changeSens && sens == 8)){
					minDistance = distance(cx-1, cy-1, centerXFourmiliere, centerYFourmiliere);
					directionX = -1;
					directionY = -1;
				}
				
				if(directionX >= 0)
					cx += directionX;
				else
					cx -= Math.abs(directionX);
				
				if(directionY >= 0)
					cy += directionY;
				else
					cy -= Math.abs(directionY);
				
			}else{
				fourmiliere.setQuantity(fourmiliere.getQuantity()+1);
				haveFood = false;
			}
		}
	}
	
	public void draw(Graphics g){		
		g.setColor(Color.black);
		
		if(drawBody){
			if(directionX == 0 && directionY == -1){
				g.fillOval(cx+size/2-4, cy+size, 8, 16);
			}else if(directionX == 1 && directionY == -1){
				int posX = cx+2;
				int posY = cy+size-2;
				int[] x = {posX-1, posX-4, posX-6, posX-10, posX-11, posX-11, posX-7, posX-5, posX-1, posX, posX};
				int[] y = {posY, posY, posY+1, posY+5, posY+8, posY+11, posY+11, posY+10, posY+6, posY+4, posY+2};
				g.fillPolygon(x, y, x.length);
			}else if(directionX == 1 && directionY == 0){
				g.fillOval(cx-16, cy+size-8, 16, 8);
			}else if(directionX == 1 && directionY == 1){
				int posX = cx+2;
				int posY = cy+2;
				int[] x = {posX, posX, posX-1, posX-5, posX-8, posX-10, posX-10, posX-9, posX-5, posX-3, posX-1};
				int[] y = {posY-1, posY-4, posY-6, posY-10, posY-11, posY-11, posY-7, posY-5, posY-1, posY, posY};
				g.fillPolygon(x, y, x.length);
			}else if(directionX == 0 && directionY == 1){
				g.fillOval(cx+size/2-4, cy-16, 8, 16);
			}else if(directionX == -1 && directionY == 1){
				int posX = cx+size-2;
				int posY = cy+2;
				int[] x = {posX, posX, posX+1, posX+5, posX+7, posX+11, posX+11, posX+10, posX+6, posX+4, posX+1};
				int[] y = {posY-1, posY-3, posY-5, posY-9, posY-10, posY-10, posY-8, posY-5, posY-1, posY, posY};
				g.fillPolygon(x, y, x.length);
			}else if(directionX == -1 && directionY == 0){
				g.fillOval(cx+size, cy+size/2-4, 16, 8);
			}else if(directionX == -1 && directionY == -1){
				int posX = cx+size-2;
				int posY = cy+size-2;
				int[] x = {posX+1, posX+3, posX+5, posX+9, posX+10, posX+10, posX+8, posX+5, posX+1, posX, posX};
				int[] y = {posY, posY, posY+1, posY+5, posY+7, posY+11, posY+11, posY+10, posY+6, posY+4, posY+1};
				g.fillPolygon(x, y, x.length);
			}
		}
		
		g.fillOval(cx, cy, 8, 8);
		if(this.isHaveFood())
			g.setColor(Color.red);
		else
			g.setColor(Color.darkGray);
		g.fillOval(cx+2,  cy+2, 4, 4);
		
	}
	
	public boolean collisionRect(Rectangle r2){
		Rectangle r1 = new Rectangle(cx, cy, this.getWidth(), this.getHeight());
		
		return r1.intersects(r2);
	}
	
	public double sqr(double a) {
		return a*a;
	}
	 
	public double distance(double x1, double y1, double x2, double y2) {
		return Math.sqrt(sqr(y2 - y1) + sqr(x2 - x1));
	}
	
	public Pheromone getPheromoneByCoord(int cx, int cy, HashSet<Pheromone> pheromones){
		for(Iterator<Pheromone> itp = pheromones.iterator(); itp.hasNext();){
			Pheromone p = itp.next();
			if(p.getCx() == cx && p.getCy() == cy){
				return p;
			}
		}
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		if (!(obj instanceof Fourmi)) {
			return false;
		}
		Fourmi other = (Fourmi) obj;
		if (id != other.id) {
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

	public boolean isHaveFood() {
		return haveFood;
	}

	public void setHaveFood(boolean haveFood) {
		this.haveFood = haveFood;
	}

	public boolean isDrawBody() {
		return drawBody;
	}

	public void setDrawBody(boolean drawBody) {
		this.drawBody = drawBody;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
