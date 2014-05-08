package com.fourmis.model;

import java.util.ArrayList;

import com.fourmis.view.Monde;

/**
 * Rerésente une simulation possédant plsuieurs paramètres
 * @author afidalgo
 *
 */
public class Simulation {

	private Monde monde;
	private ArrayList<Fourmi> fourmis;
	private ArrayList<Pheromone> pheromones;
	private Fourmiliere fourmiliere;
	private Options options;
	
	public Simulation(Options options){
		this.options = options;
		this.fourmis = new ArrayList<>();
		for (int i = 0; i < options.getNombreFourmis(); i++) {
			Fourmi f = new Fourmi(0,0);
			fourmis.add(f);
		}
		
		this.pheromones = new ArrayList<>();
		this.fourmiliere = new Fourmiliere(0,0);
		this.monde = new Monde(this);
	}
	
	public void nextStep(){
		for (Fourmi f : fourmis) {
			f.move();
		}
	}

	public ArrayList<Fourmi> getFourmis() {
		return fourmis;
	}

	public void setFourmis(ArrayList<Fourmi> fourmis) {
		this.fourmis = fourmis;
	}

	public Monde getMonde() {
		return monde;
	}

	public void setMonde(Monde monde) {
		this.monde = monde;
	}

	public Options getOptions() {
		return options;
	}
	
	
	
	
}
