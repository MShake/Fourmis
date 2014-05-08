package com.fourmis.view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.fourmis.model.Fourmi;

/**
 * Représente le JPanel ou seront affiché les éléments (fourmilière, fourmis, nourriture ...)
 * @author afidalgo
 *
 */
public class Terrain extends JPanel{

	private ArrayList<Fourmi> fourmis = new ArrayList<>();
	
	public Terrain(){
		this.setBackground(Color.white);
	
	}

	public ArrayList<Fourmi> getFourmis() {
		return fourmis;
	}

	public void setFourmis(ArrayList<Fourmi> fourmis) {
		this.fourmis = fourmis;
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(Fourmi f : fourmis){
			f.setHaveFood(true);
			f.draw(g);
		}
	}
}
