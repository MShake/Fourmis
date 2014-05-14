package com.fourmis.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.fourmis.model.Simulation;

/**
 * Représente la JFrame principal de la simulation
 * @author afidalgo
 *
 */
public class Monde extends JFrame implements ChangeListener{
	
	private Simulation sim;
	private Terrain terrain;
	private JPanel pan = new JPanel(new BorderLayout());
	private JPanel stat = new JPanel();
	private JSlider speed = new JSlider(10, 100, 50);
	private JLabel stats = new JLabel("statistiques");
	
	
	public Monde(Simulation sim){
		this.terrain = new Terrain(sim);
		this.sim = sim;
		this.terrain.setBackground(new Color(41, 181, 60));
		this.setTitle("Fourmis : Simulation");
		this.setSize(sim.getOptions().getSizeScreen(), sim.getOptions().getSizeScreen());
		this.setLocation(700, 100);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image favicon = kit.getImage("res/img/favicon.png");
		this.setIconImage(favicon);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		stat.add(stats);
		
		this.pan.add(stat, BorderLayout.NORTH);
		this.pan.add(terrain, BorderLayout.CENTER);
		this.pan.add(speed, BorderLayout.SOUTH);
		this.setContentPane(pan);
		this.setVisible(true);
	}

	public Terrain getTerrain() {
		return terrain;
	}

	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if(e.getSource() == speed){
			this.sim.getOptions().setTime(speed.getValue());
		}
		
	}
	
}
