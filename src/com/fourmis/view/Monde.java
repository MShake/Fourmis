package com.fourmis.view;

import javax.swing.JFrame;

import com.fourmis.model.Simulation;

/**
 * Représente la JFrame principal de la simulation
 * @author afidalgo
 *
 */
public class Monde extends JFrame{
	
	public Terrain terrain;
	
	public Monde(Simulation sim){
		this.terrain = new Terrain(sim);
		this.setTitle("Fourmis : Simulation");
		this.setSize(500, 500);
		this.setLocation(700, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(terrain);
		this.setVisible(true);
	}

	public Terrain getTerrain() {
		return terrain;
	}

	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}
	
}
