package com.fourmis.view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Représente la JFrame principal de la simulation
 * @author afidalgo
 *
 */
public class Monde extends JFrame{
	
	public Terrain terrain;
	
	public Monde(){
		this.terrain = new Terrain();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Fourmis : Simulation");
		this.setSize(500, 500);
		this.setLocation(700, 100);
		
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
