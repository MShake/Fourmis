package com.fourmis.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.fourmis.model.Fourmi;
import com.fourmis.model.Nourriture;
import com.fourmis.model.Pheromone;
import com.fourmis.model.Simulation;

/**
 * Représente le JPanel ou seront affiché les éléments (fourmilière, fourmis, nourriture ...)
 *
 */
public class Terrain extends JPanel{

	private Simulation sim;
	
	public Terrain(Simulation sim){
		this.sim = sim;
		this.setBackground(Color.white);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		sim.getFourmiliere().draw(g);
		
		for(Pheromone p : sim.getPheromones()){
			p.draw(g);
		}
		
		for(Nourriture n : sim.getNourritures()){
			n.draw(g);
		}
		
		for(Fourmi f : sim.getFourmiliere().getFourmis()){
			f.draw(g);
		}
		
		
		
	}
}
