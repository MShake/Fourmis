package com.fourmis.view;

import java.awt.BorderLayout;
import java.awt.Color;

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
public class Monde extends JFrame implements  ChangeListener{
	
	private Terrain terrain;
	private JPanel pan = new JPanel(new BorderLayout());
	private JPanel stat = new JPanel();
	private JSlider speed = new JSlider(10, 100, 50);
	private JLabel stats = new JLabel("statistiques");
	
	public Monde(Simulation sim){
		this.terrain = new Terrain(sim);
		this.terrain.setBackground(new Color(41, 181, 60));
		this.setTitle("Fourmis : Simulation");
		this.setSize(sim.getOptions().getSizeScreen(), sim.getOptions().getSizeScreen());
		this.setLocation(700, 100);
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
			//TODO changer valeur dans la simulation
		}
		
	}
	
}
