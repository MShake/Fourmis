package com.fourmis.controller;


import javax.swing.Timer;

import com.fourmis.bean.Options;
import com.fourmis.bean.Simulation;

public class Controleur {
	
	private Simulation sim;
	private static Timer timer;
	
	public Controleur(Options options){
		this.sim = new Simulation(options);
	}
	
	public void run(){
		setTimer(new Timer(this.sim.getOptions().getTime(), e -> {
			getTimer().setDelay(sim.getOptions().getTime());
			sim.nextStep();
			sim.getMonde().repaint();
			sim.getMonde().getTerrain().repaint();
		}));
		getTimer().setRepeats(true);
		getTimer().start();
	}

	public static Timer getTimer() {
		return timer;
	}

	private static void setTimer(Timer timer) {
		Controleur.timer = timer;
	}
}
