package com.fourmis.view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.fourmis.model.Fourmi;
import com.fourmis.model.Simulation;

/**
 * Repr�sente le JPanel ou seront affich� les �l�ments (fourmili�re, fourmis, nourriture ...)
 * @author afidalgo
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
		for(Fourmi f : sim.getFourmis()){
			f.draw(g);
		}
	}
}
