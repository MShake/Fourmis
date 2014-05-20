package com.fourmis.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class Fourmi extends JPanel implements Insecte{
	
	private final double CHANGE_DIR = 0.005;
	private double cx; 					// coordonn�e en x
	private double cy; 					// coordonn�e en y
	private boolean haveFood = false;	// poss�de de la nourriture
	private int size = 8;				// taille de la fourmi
	private int sens = 0;				// direction (en hexagone)
	private int maxX;					// valeur maximal de la fen�tre en x
	private int maxY;					// valeur maximal de la fen�tre en y
	private int directionX = 0;
	private int directionY = 0;
	
	public Fourmi(double cx, double cy, int maxX, int maxY){
		
		this.cx = cx;
		this.cy = cy;
		this.maxX = maxX;
		this.maxY = maxY;
	
	    this.setPreferredSize(new Dimension(this.size, this.size));
	    
	    this.setOpaque(false);
	}
	
	public void move(Fourmiliere fourmiliere, ArrayList<Nourriture> nourritures, ArrayList<Pheromone> pheromones){
		if(!this.haveFood){
			boolean findPheromone = false;
			for(Pheromone p : pheromones){
				if((int)p.getCx() == (int)cx && (int)p.getCy() == (int)cy){
					findPheromone = true;
					break;
				}
			}
			
			if(findPheromone && !(cx == fourmiliere.getCx()+fourmiliere.getWidth()/2-size/2 && cy == fourmiliere.getCy()+fourmiliere.getHeight()/2-size/2)){
				int minQuantity = Integer.MAX_VALUE;
				int nbSourcesPheromone = 0;
				for(Pheromone p : pheromones){
					int posXPheromone = (int) p.getCx();
					int posYPheromone = (int) p.getCy();
					int posXFourmi = (int) cx;
					int posYFourmi = (int) cy;
					
					if((posXPheromone == posXFourmi && posYPheromone == posYFourmi-1) ||
							(posXPheromone == posXFourmi+1 && posYPheromone == posYFourmi-1) ||
							(posXPheromone == posXFourmi+1 && posYPheromone == posYFourmi+1) ||
							(posXPheromone == posXFourmi && posYPheromone == posYFourmi+1) || 
							(posXPheromone == posXFourmi-1 && posYPheromone == posYFourmi+1) ||
							(posXPheromone == posXFourmi-1 && posYPheromone == posYFourmi-1) ||
							(posXPheromone == posXFourmi-1 && posYPheromone == posYFourmi) ||
							(posXPheromone == posXFourmi+1 && posYPheromone == posYFourmi)){
						nbSourcesPheromone++;
					}
				}
				
				if(nbSourcesPheromone >= 2){
					for(Pheromone p : pheromones){
						int posXPheromone = (int) p.getCx();
						int posYPheromone = (int) p.getCy();
						int posXFourmi = (int) cx;
						int posYFourmi = (int) cy;
						
						if(posXPheromone == posXFourmi && posYPheromone == posYFourmi-1){
							if(p.getQuantity() < minQuantity){
								directionX = 0;
								directionY = -1;
								minQuantity = p.getQuantity();
							}
						}else if(posXPheromone == posXFourmi+1 && posYPheromone == posYFourmi-1){
							if(p.getQuantity() < minQuantity){
								directionX = 1;
								directionY = -1;
								minQuantity = p.getQuantity();
							}
						}else if(posXPheromone == posXFourmi+1 && posYPheromone == posYFourmi+1){
							if(p.getQuantity() < minQuantity){
								directionX = 1;
								directionY = 1;
								minQuantity = p.getQuantity();
							}
						}else if(posXPheromone == posXFourmi && posYPheromone == posYFourmi+1){
							if(p.getQuantity() < minQuantity){
								directionX = 0;
								directionY = 1;
								minQuantity = p.getQuantity();
							}
						}else if(posXPheromone == posXFourmi-1 && posYPheromone == posYFourmi+1){
							if(p.getQuantity() < minQuantity){
								directionX = -1;
								directionY = 1;
								minQuantity = p.getQuantity();
							}
						}else if(posXPheromone == posXFourmi-1 && posYPheromone == posYFourmi-1){
							if(p.getQuantity() < minQuantity){
								directionX = -1;
								directionY = -1;
								minQuantity = p.getQuantity();
							}
						}else if(posXPheromone == posXFourmi-1 && posYPheromone == posYFourmi){
							if(p.getQuantity() < minQuantity){
								directionX = -1;
								directionY = 0;
								minQuantity = p.getQuantity();
							}
						}else if(posXPheromone == posXFourmi+1 && posYPheromone == posYFourmi){
							if(p.getQuantity() < minQuantity){
								directionX = 1;
								directionY = 0;
								minQuantity = p.getQuantity();
							}
						}
					}
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
				//Changement de sens al�atoire ou si elle touche le bord de la fen�tre
				boolean changeSens = false;
				if(Math.random() < CHANGE_DIR || cx == 0 || cy == 0 || cx == maxX || cy == maxY || sens == 0){
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
			}
			
			//Regarde si la fourmi est sur une source de nourriture
			for(Nourriture n : nourritures){
				int centerXFourmi = (int)cx+(size/2);
				int centerYFourmi = (int)cy+(size/2);
				if(centerXFourmi >= n.getCx() && centerXFourmi <= n.getCx()+n.getWidth() && centerYFourmi >= n.getCy() && centerYFourmi <= n.getCy()+n.getHeight() && n.getQuantity() > 0){
					this.haveFood = true;
					n.setQuantity(n.getQuantity()-1);
					break;
				}
			}
		}else{
			//Gestion du mouvement de la fourmi dans le cas o� elle a de la nourriture
			directionX = 0;
			directionY = 0;
			int centerXFourmiliere = fourmiliere.getCx()+fourmiliere.getWidth()/2 - size/2;
			int centerYFourmiliere = fourmiliere.getCy()+fourmiliere.getHeight()/2 - size/2;
			if((int)cx != centerXFourmiliere || (int)cy != centerYFourmiliere){
				double stepX = 1; //initailisation du pas de d�placement en x
				double stepY = 1; //initailisation du pas de d�placement en y
				
				if(Math.abs(centerYFourmiliere-this.cy) != 0){
					stepX = Math.abs(centerXFourmiliere-this.cx) / Math.abs(centerYFourmiliere-this.cy);
					if(stepX > 1)
						stepX = 1;
				}
				if(Math.abs(centerXFourmiliere-this.cx) != 0){
					stepY = Math.abs(centerYFourmiliere-this.cy) / Math.abs(centerXFourmiliere-this.cx);
					if(stepY > 1)
						stepY = 1;
				}
				
				//gestion de la direction la plus rapide pour aller � la fourmili�re
				if(cx < centerXFourmiliere && cy < centerYFourmiliere){
					cx += stepX;
					cy += stepY;
				}else if(cx < centerXFourmiliere && cy >= centerYFourmiliere){
					cx += stepX;
					cy -= stepY;
				}else if(cx >= centerXFourmiliere && cy >= centerYFourmiliere){
					cx -= stepX;
					cy -= stepY;
				}else if(cx >= centerXFourmiliere && cy < centerYFourmiliere){
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
		g.fillOval((int)cx, (int)cy, 8, 8);
		if(this.isHaveFood())
			g.setColor(Color.red);
		else
			g.setColor(Color.darkGray);
		g.fillOval((int)cx+2,  (int)cy+2, 4, 4);
	}
	
//	public Pheromone deposePheromone(){
//		return new Pheromone(this.cx, this.cy);
//	}

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
