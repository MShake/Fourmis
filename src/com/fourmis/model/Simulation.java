package com.fourmis.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.fourmis.view.Monde;

/**
 * Repr�sente une simulation poss�dant plusieurs param�tres
 *
 */
public class Simulation {

	private Monde monde;
	private ArrayList<Nourriture> nourritures;
	private ArrayList<Pheromone> pheromones;
	private ArrayList<Predator> predators;
	private Fourmiliere fourmiliere;
	private Options options;
	private int wildFood = 0;
	
	public Simulation(Options options){
		this.options = options;
		this.monde = new Monde(this);
		this.fourmiliere = new Fourmiliere(0, 0, 40, 40);
		Random rand = new Random();
		int positionX = rand.nextInt(this.getMonde().getTerrain().getWidth()-this.fourmiliere.getWidth() - 1);
		int positionY = rand.nextInt(this.getMonde().getTerrain().getHeight()-this.fourmiliere.getHeight() - 1);
		this.fourmiliere.setCx(positionX);
		this.fourmiliere.setCy(positionY);
		
		for (int i = 0; i < options.getNombreFourmis(); i++) {
			Fourmi f = new Fourmi(positionX+this.fourmiliere.getWidth()/2-4, positionY+this.fourmiliere.getHeight()/2-4, this.getMonde().getTerrain().getWidth()-8, this.getMonde().getTerrain().getHeight()-8);
			this.fourmiliere.getFourmis().add(f);
		}
		this.getMonde().getNbFourmis().setText("Fourmis : "+fourmiliere.getFourmis().size());
		
		this.nourritures = new ArrayList<Nourriture>();
		for(int i = 0; i < options.getNombreNourritures(); i++){
			int quantity = rand.nextInt(50 - 10+1) + 10;
			do{
				positionX = rand.nextInt(this.getMonde().getTerrain().getWidth()-quantity - 1);
				positionY = rand.nextInt(this.getMonde().getTerrain().getHeight()-quantity - 1);
			}while(fourmiliere.collidepoint(positionX, positionY, quantity, quantity));
			Nourriture n = new Nourriture(positionX, positionY, quantity);
			wildFood += n.getQuantity();
			this.nourritures.add(n);
		}
		this.getMonde().getWildFood().setText("Wild Food : "+wildFood);
		this.pheromones = new ArrayList<>();
		
		this.predators = new ArrayList<>();
		for(int i=0; i<options.getNombreCoccinelles(); i++){
			Coccinelle c = new Coccinelle(0, 0, this.getMonde().getTerrain().getWidth()-16, this.getMonde().getTerrain().getHeight()-16);
			predators.add(c);
		}
		
		for(int i=0; i<options.getNombreFourmiliers(); i++){
			Fourmilier f = new Fourmilier(0, 0, this.getMonde().getTerrain().getWidth()-16, this.getMonde().getTerrain().getHeight()-16);
			predators.add(f);
		}
	}
	
	public void nextStep(){
		for (Fourmi f : fourmiliere.getFourmis()) {
			double centerXFourmi = f.getCx()+f.getWidth()/2;
			double centerYFourmi = f.getCy()+f.getHeight()/2;
			if(f.isHaveFood()){
				if(issetPheromone(centerXFourmi, centerYFourmi)){
					getPheromoneByCoord(centerXFourmi, centerYFourmi).setQuantity(getPheromoneByCoord(centerXFourmi, centerYFourmi).getQuantity()+100);
				}
				else
					pheromones.add(new Pheromone(centerXFourmi, centerYFourmi));
			}
			f.move(fourmiliere, nourritures, pheromones);
		}
		this.getMonde().getNbFourmis().setText("Fourmis : "+fourmiliere.getFourmis().size());
		
		for(Predator p : predators){
			if(p instanceof Coccinelle){
				Coccinelle c = (Coccinelle) p;
				c.move(nourritures);
			}else if(p instanceof Fourmilier){
				Fourmilier f = (Fourmilier) p;
				f.move(fourmiliere.getFourmis());
			}
		}
		
		wildFood = 0;
		for (Iterator<Nourriture> it = nourritures.iterator(); it.hasNext(); ) {
			Nourriture n = it.next();
			if(n.getQuantity() <= 0){
				it.remove();
			}
			wildFood += n.getQuantity();
		}
		this.getMonde().getWildFood().setText("Wild Food : "+wildFood);
		
		for (Iterator<Pheromone> itp = pheromones.iterator(); itp.hasNext(); ) {
			Pheromone p = itp.next();
			p.setQuantity(p.getQuantity()-1);
			if(p.getQuantity() < 1){
				itp.remove();
			}
		}
		
		this.getMonde().getQgFood().setText("QG Food : "+fourmiliere.getQuantity());
	}
	
	public boolean issetPheromone(double cx, double cy){
		for(Pheromone p : pheromones){
			if(p.getCx() == cx && p.getCy() == cy)
				return true;
		}
		return false;
	}
	
	public Pheromone getPheromoneByCoord(double cx, double cy){
		for(Pheromone p : pheromones){
			if(p.getCx() == cx && p.getCy() == cy)
				return p;
		}
		return null;
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

	public ArrayList<Nourriture> getNourritures() {
		return nourritures;
	}

	public void setNourritures(ArrayList<Nourriture> nourritures) {
		this.nourritures = nourritures;
	}

	public ArrayList<Pheromone> getPheromones() {
		return pheromones;
	}

	public void setPheromones(ArrayList<Pheromone> pheromones) {
		this.pheromones = pheromones;
	}

	public ArrayList<Predator> getPredators() {
		return predators;
	}

	public void setPredators(ArrayList<Predator> predators) {
		this.predators = predators;
	}
	
	
	
}
