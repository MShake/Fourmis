package com.fourmis.view;

import java.awt.Frame;
import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.fourmis.bean.Fourmi;
import com.fourmis.bean.Nourriture;
import com.fourmis.bean.Obstacle;
import com.fourmis.bean.Pheromone;
import com.fourmis.bean.Predator;
import com.fourmis.bean.Simulation;

/**
 * Représente le JPanel ou seront affiché les éléments (fourmilière, fourmis, nourriture ...)
 *
 */
public class Terrain extends JPanel{

	private Simulation sim;
	private ImageIcon image = new ImageIcon(Frame.class.getResource("/com/fourmis/ressources/img/herbe.jpg"));
	
	Terrain(Simulation sim){
		this.sim = sim;
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(image.getImage(), 0, 0, sim.getOptions().getSizeScreen(),sim.getOptions().getSizeScreen(), null); 
		
		sim.getFourmiliere().draw(g);
		
		for (Nourriture n : sim.getNourritures()){
			n.draw(g);
		}
		
		for (Obstacle o : sim.getObstacles()){
			o.draw(g);
		}

		for (Pheromone p : sim.getPheromones()) {
			p.draw(g);
		}

		for (Fourmi f : sim.getFourmiliere().getFourmis()) {
			f.draw(g);
		}
		
		for(Predator p : sim.getPredators()){
			p.draw(g);
		}
		
	}
}
