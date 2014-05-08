package com.fourmis.model;

import java.awt.Dimension;

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
	
	public void move(){
		double random = Math.random();
		
		if(random < 0.125 && cy > 0){
			cy--;
		}
		else if((random < 0.25)){
			cy--;
			cx++;
		}
		else if((random < 0.375) ){
			cx++;
		}
		else if((random < 0.5) ){
			cx++;
			cy++;
		}
		else if((random < 0.625)){
			cy++;
		}
		else if((random < 0.75)){
			cx--;
			cy++;
		}
		else if((random < 0.875)){
			cx--;
		}
		else if((random < 1)){
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
