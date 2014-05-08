package com.fourmis.model;

import java.util.ArrayList;

import com.fourmis.view.Monde;

/**
 * Rer�sente une simulation poss�dant plsuieurs param�tres
 * @author afidalgo
 *
 */
public class Simulation {

	private Monde monde;
	private ArrayList<Fourmi> fourmis;
	private ArrayList<Pheromone> pheromones;
	private Fourmiliere fourmiliere;
	
	public Simulation(Options options){
		this.monde = new Monde();
		this.fourmis = new ArrayList<>();
		for (int i = 0; i < options.getNombreFourmis(); i++) {
			Fourmi f = new Fourmi(0,0);
			
			fourmis.add(f);
			this.monde.terrain.getFourmis().add(f);
		}
		this.pheromones = new ArrayList<>();
		this.fourmiliere = new Fourmiliere(0,0);
	}
	
	public void nextStep(){
		for (Fourmi f : fourmis) {
			f.move();
			System.out.println(f.getCx());
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
	
	
	
	
}
