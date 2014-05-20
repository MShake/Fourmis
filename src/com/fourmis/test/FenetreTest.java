package com.fourmis.test;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.fourmis.model.Coccinelle;
import com.fourmis.model.Fourmilier;
import com.fourmis.model.Fourmiliere;
import com.fourmis.model.Simulation;

public class FenetreTest extends JFrame {
	private Fourmiliere fourmiliere;
	private Simulation sim;

	public FenetreTest()  {
		MyJpanel p = new MyJpanel();
		p.setPreferredSize(new Dimension(400, 400));
		this.setSize(400,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(100,100);
		
		
		this.setContentPane(p);
		this.setVisible(true);
		
	}


}
