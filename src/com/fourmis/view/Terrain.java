package com.fourmis.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import com.fourmis.model.Fourmi;
import com.fourmis.model.Nourriture;
import com.fourmis.model.Pheromone;
import com.fourmis.model.Simulation;

/**
 * Repr�sente le JPanel ou seront affich� les �l�ments (fourmili�re, fourmis, nourriture ...)
 *
 */
public class Terrain extends JPanel{

	private Simulation sim;
	Toolkit kit = Toolkit.getDefaultToolkit();
	Image icone = kit.getImage("res/img/herbe.jpg");
	
	public Terrain(Simulation sim){
		this.sim = sim;
		
		this.setBackground(new Color(41, 181, 60));
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(icone ,0, 0,sim.getOptions().getSizeScreen(),sim.getOptions().getSizeScreen(), null); 
		
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
