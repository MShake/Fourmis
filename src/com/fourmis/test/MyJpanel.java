package com.fourmis.test;

import java.awt.Graphics;

import javax.swing.JPanel;

import com.fourmis.model.Coccinelle;
import com.fourmis.model.Fourmi;
import com.fourmis.model.Fourmilier;
import com.fourmis.model.Nourriture;
import com.fourmis.model.Pheromone;
import com.fourmis.model.Simulation;

public class MyJpanel extends JPanel {
	Coccinelle c = new Coccinelle(50,50, 100, 100);
	Fourmilier f = new Fourmilier(200,200, 400, 400);

	private Simulation sim;
	public MyJpanel() {

	}
	public void paintComponent(Graphics g){
			c.draw(g);
			f.draw(g);
		
		
		
		
	}

}
