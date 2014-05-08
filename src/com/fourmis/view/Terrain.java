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
			g.setColor(Color.black);
			g.fillOval(f.getCx()+this.getWidth()/2-4, f.getCy()+this.getHeight()/2-4, 8, 8);
			if(f.isHaveFood())
				g.setColor(Color.red);
			else
				g.setColor(Color.white);
			g.fillOval(f.getCx()+this.getWidth()/2-2,  f.getCy()+this.getHeight()/2-2, 4, 4);
		
		}
	}
}
