package com.fourmis.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class Container extends JPanel {

	public Container() {
		super();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image icone = kit.getImage("res/img/herbe.jpg");
		g.drawImage(icone ,0, 0,626,417, null); 
	}
}
