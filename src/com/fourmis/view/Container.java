package com.fourmis.view;

import java.awt.Frame;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Container extends JPanel {
	
	private ImageIcon image = new ImageIcon(Frame.class.getResource("/com/fourmis/ressources/img/herbe.jpg"));
	
	public Container() {
		super();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image.getImage(), 0, 0, 500, 417, null);
	}
}
