package com.fourmis.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import com.fourmis.view.Terrain;

import javax.swing.JPanel;

public class Fourmi extends JPanel{
	private int cx; //coordonnée en x
	private int cy; //coordonnée en y
	private boolean haveFood;
	
	public Fourmi(int cx, int cy){
		
		this.cx = cx;
		this.cy = cy;
	
	    this.setPreferredSize(new Dimension(8, 8));
	   
	    
	    this.setOpaque(false);
	}
	
	public void draw(Graphics g){
		g.setColor(Color.black);
		g.fillOval(this.getCx()+this.getWidth()/2-4, this.getCy()+this.getHeight()/2-4, 8, 8);
		if(this.isHaveFood())
			g.setColor(Color.red);
		else
			g.setColor(Color.white);
		g.fillOval(this.getCx()+this.getWidth()/2-2,  this.getCy()+this.getHeight()/2-2, 4, 4);
	}
	
//	public void paintComponent(Graphics g){
//		super.paintComponent(g);
//		
//		g.setColor(Color.black);
//		g.fillOval(cx+this.getWidth()/2-4, cy+this.getHeight()/2-4, 8, 8);
//		g.setColor(Color.red);
//		g.fillOval(cx+this.getWidth()/2-2, cy+this.getHeight()/2-2, 4, 4);
//	}
	
	public void move(){
		double random = Math.random();
		
		if(random < 0.125 && cy > 0){
			cy--;
		}
		else if((random >= 0.125 && random < 0.25)){
			cy--;
			cx++;
		}
		else if((random >= 0.25 && random < 0.375) ){
			cx++;
		}
		else if((random >= 0.375 && random < 0.5) ){
			cx++;
			cy++;
		}
		else if((random >= 0.5 && random < 0.625)){
			cy++;
		}
		else if((random >= 0.625 && random < 0.75)){
			cx--;
			cy++;
		}
		else if((random >= 0.75 && random < 0.875)){
			cx--;
		}
		else if((random >= 0.875 && random < 1)){
			cx--;
			cy--;
		}
		
		this.setCx(cx);
		this.setCy(cy);
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
