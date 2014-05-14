package com.fourmis.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.fourmis.model.Options;
import com.fourmis.model.Rendu;
import com.fourmis.model.Simulation;

public class Controleur {
	
	private Simulation sim;
	private Rendu rendu;
	private Timer timer;
	
	public Controleur(Options options){
		this.sim = new Simulation(options);
		this.rendu = new Rendu();
	}
	
	public void run(){
		timer = new Timer(this.sim.getOptions().getTime(), new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	timer.setDelay(sim.getOptions().getTime());
		    	sim.nextStep();
				rendu.paint(sim);
		    }
		});
		timer.setRepeats(true);
		timer.start();
	}
}
