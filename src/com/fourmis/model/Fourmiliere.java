package com.fourmis.model;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Fourmiliere extends JPanel{
	private int cx; //coordonnée en x
	private int cy; //coordonnée en y
	
	public Fourmiliere(int cx, int cy){
		this.cx = cx;
		this.cy = cy;
		
	}
}
