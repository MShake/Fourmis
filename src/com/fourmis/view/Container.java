package com.fourmis.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Container extends JPanel {

	public Image image;	
	
	public Container() {
		 image = Toolkit.getDefaultToolkit().getImage("herbe.jpg");
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);

	    g.drawImage (image, 5, 5, null);
		
	}

}
