package com.fourmis.test;

import java.awt.Graphics;

import javax.swing.JPanel;

import com.fourmis.bean.Coccinelle;
import com.fourmis.bean.Fourmilier;
import com.fourmis.bean.Simulation;

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
