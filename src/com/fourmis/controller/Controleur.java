package com.fourmis.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.fourmis.bean.Options;
import com.fourmis.bean.Rendu;
import com.fourmis.bean.Simulation;

public class Controleur {
	
	private Simulation sim;
	private Rendu rendu;
	private static Timer timer;
	
	public Controleur(Options options){
		this.sim = new Simulation(options);
		this.rendu = new Rendu();
	}
	
	public void run(){
		setTimer(new Timer(this.sim.getOptions().getTime(), new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	getTimer().setDelay(sim.getOptions().getTime());
		    	sim.nextStep();
				rendu.paint(sim);
		    }
		}));
		getTimer().setRepeats(true);
		getTimer().start();
	}

	public static Timer getTimer() {
		return timer;
	}

	public static void setTimer(Timer timer) {
		Controleur.timer = timer;
	}
}
