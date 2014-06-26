package com.fourmis.test;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.fourmis.bean.Fourmiliere;
import com.fourmis.bean.Simulation;

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
