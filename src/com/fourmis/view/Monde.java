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
 * Repr�sente la JFrame principale de la simulation
 *
 */
public class Monde extends JFrame implements ChangeListener{
	
	private Simulation sim;
	private Terrain terrain;
	private JPanel pan = new JPanel(new BorderLayout());
	private JPanel stat = new JPanel();
	private JSlider speed;
	private JLabel qgFood;
	private JLabel wildFood = new JLabel("Wild Food : ");
	private JLabel nbFourmis = new JLabel("Fourmis : ");
	
	
	public Monde(Simulation sim){
		this.terrain = new Terrain(sim);
		this.sim = sim;
		this.speed = new JSlider(1, 100, 1+100-sim.getOptions().getTime());
		this.qgFood = new JLabel("QG Food : 0");
		this.setTitle("Fourmis : Simulation");
		this.setSize(sim.getOptions().getSizeScreen(), sim.getOptions().getSizeScreen());
		this.setLocation(700, 100);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image favicon = kit.getImage("res/img/favicon.png");
		this.setIconImage(favicon);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		stat.add(qgFood);
		stat.add(wildFood);
		stat.add(nbFourmis);
		speed.addChangeListener(this);
		
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
			this.sim.getOptions().setTime(speed.getMinimum()+speed.getMaximum() - speed.getValue());
		}
	}

	public JLabel getQgFood() {
		return qgFood;
	}

	public void setQgFood(JLabel qgFood) {
		this.qgFood = qgFood;
	}

	public JLabel getWildFood() {
		return wildFood;
	}

	public void setWildFood(JLabel wildFood) {
		this.wildFood = wildFood;
	}

	public JLabel getNbFourmis() {
		return nbFourmis;
	}

	public void setNbFourmis(JLabel nbFourmis) {
		this.nbFourmis = nbFourmis;
	}
	
	
}
