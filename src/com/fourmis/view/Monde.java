package com.fourmis.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.fourmis.bean.Simulation;
import com.fourmis.controller.Controleur;

/**
 * Représente la JFrame principale de la simulation
 *
 */
public class Monde extends JFrame implements ChangeListener, ActionListener{
	
	private Simulation sim;
	private Terrain terrain;
	private JPanel pan = new JPanel(new BorderLayout());
	private JPanel stat = new JPanel();
	private JPanel footer = new JPanel();
	private JSlider speed;
	private JLabel qgFood;
	private JLabel wildFood = new JLabel("Wild Food : ");
	private JLabel nbFourmis = new JLabel("Fourmis : ");
	private JLabel nbPheromones = new JLabel("Phéromones : 0");
	private ImageIcon play = new ImageIcon(Frame.class.getResource("/com/fourmis/ressources/img/play.png"));
	private ImageIcon pause = new ImageIcon(Frame.class.getResource("/com/fourmis/ressources/img/pause.png"));
	private JButton running = new JButton(pause);
	
	public Monde(Simulation sim){
		this.terrain = new Terrain(sim);
		this.sim = sim;
		this.speed = new JSlider(1, 100, 1+100-sim.getOptions().getTime());
		this.qgFood = new JLabel("QG Food : 0");
		
		this.setTitle("Fourmis : Simulation");
		this.setSize(sim.getOptions().getSizeScreen(), sim.getOptions().getSizeScreen());
		this.setLocation(700, 100);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image favicon = kit.getImage(Frame.class.getResource("/com/fourmis/ressources/img/favicon.png"));
		this.setIconImage(favicon);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		stat.add(qgFood);
		stat.add(wildFood);
		stat.add(nbFourmis);
		stat.add(nbPheromones);
		speed.addChangeListener(this);
		
		this.running.setPreferredSize(new Dimension(30, 30));
		this.running.addActionListener(this);
		
		this.footer.setLayout(new BorderLayout());
		this.footer.add(speed, BorderLayout.CENTER);
		this.footer.add(running, BorderLayout.EAST);
		
		this.pan.add(stat, BorderLayout.NORTH);
		this.pan.add(terrain, BorderLayout.CENTER);
		this.pan.add(footer, BorderLayout.SOUTH);
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

	public JLabel getNbPheromones() {
		return nbPheromones;
	}

	public void setNbPheromones(JLabel nbPheromones) {
		this.nbPheromones = nbPheromones;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(Controleur.getTimer() != null && e.getSource() == running){
			if(Controleur.getTimer().isRunning()){
				Controleur.getTimer().stop();
				running.setIcon(play);
			}else{
				Controleur.getTimer().start();
				running.setIcon(pause);
			}
		}
		
	}
	
	
}
