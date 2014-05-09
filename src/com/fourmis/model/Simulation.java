package com.fourmis.model;

import java.util.ArrayList;
import java.util.Random;

import com.fourmis.view.Monde;

/**
 * Rerésente une simulation possédant plsuieurs paramètres
 *
 */
public class Simulation {

	private Monde monde;
	private ArrayList<Fourmi> fourmis;
	private ArrayList<Pheromone> pheromones;
	private Fourmiliere fourmiliere;
	private Options options;
	
	public Simulation(Options options){
		this.monde = new Monde(this);
		this.options = options;
		this.fourmiliere = new Fourmiliere(0, 0, 32, 32);
		Random rand = new Random();
		int positionX = rand.nextInt(this.getMonde().getTerrain().getWidth()-this.fourmiliere.getWidth() - 1);
		int positionY = rand.nextInt(this.getMonde().getTerrain().getHeight()-this.fourmiliere.getHeight() - 1);
		this.fourmiliere.setCx(positionX);
		this.fourmiliere.setCy(positionY);
		
		this.fourmis = new ArrayList<>();
		for (int i = 0; i < options.getNombreFourmis(); i++) {
			Fourmi f = new Fourmi(positionX+this.fourmiliere.getWidth()/2-4, positionY+this.fourmiliere.getHeight()/2-4, this.getMonde().getTerrain().getWidth()-8, this.getMonde().getTerrain().getHeight()-8);
			fourmis.add(f);
		}
		
		this.pheromones = new ArrayList<>();
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

	public Fourmiliere getFourmiliere() {
		return fourmiliere;
	}

	public void setFourmiliere(Fourmiliere fourmiliere) {
		this.fourmiliere = fourmiliere;
	}
	
}
