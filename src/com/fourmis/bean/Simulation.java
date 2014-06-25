package com.fourmis.bean;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

import com.fourmis.view.Monde;

/**
 * Représente une simulation possédant plusieurs paramêtres
 *
 */
public class Simulation {

	private Monde monde;
	private ArrayList<Nourriture> nourritures;
	private HashSet<Pheromone> pheromones;
	private ArrayList<Predator> predators;
	private ArrayList<Obstacle> obstacles;
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
			f.setDrawBody(options.isPaintBody());
			f.setId(i+1);
			this.fourmiliere.getFourmis().add(f);
		}
		this.getMonde().getNbFourmis().setText("Fourmis : "+fourmiliere.getFourmis().size());
		
		this.nourritures = new ArrayList<Nourriture>();
		for(int i = 0; i < options.getNombreNourritures(); i++){
			int quantity = rand.nextInt(50 - 10+1) + 10;
			do{
				positionX = rand.nextInt(this.getMonde().getTerrain().getWidth()-quantity - 1);
				positionY = rand.nextInt(this.getMonde().getTerrain().getHeight()-quantity - 1);
			}while(fourmiliere.collisionRect(new Rectangle(positionX, positionY, quantity, quantity)));
			Nourriture n = new Nourriture(positionX, positionY, quantity);
			wildFood += n.getQuantity();
			this.nourritures.add(n);
		}
		this.getMonde().getWildFood().setText("Wild Food : "+wildFood);
		this.pheromones = new HashSet<>();
		
		this.predators = new ArrayList<>();
		for(int i=0; i<options.getNombreCoccinelles(); i++){
			Coccinelle c = new Coccinelle(0, 0, this.getMonde().getTerrain().getWidth()-16, this.getMonde().getTerrain().getHeight()-16);
			predators.add(c);
		}
		
		for(int i=0; i<options.getNombreFourmiliers(); i++){
			Fourmilier f = new Fourmilier(0, 0, this.getMonde().getTerrain().getWidth()-16, this.getMonde().getTerrain().getHeight()-16);
			predators.add(f);
		}
		
		this.obstacles = new ArrayList<Obstacle>();
		//TODO : Mettre la valeur du nombre d'obstacles ici
		for(int i=0; i<5; i++){
			int size = 30;
			boolean isCollision;
			do{
				isCollision = false;
				positionX = rand.nextInt(this.getMonde().getTerrain().getWidth()-size-20 - 20+1)+20;
				positionY = rand.nextInt(this.getMonde().getTerrain().getHeight()-size-20 - 20+1)+20;
				for(Nourriture n : nourritures){
					if(n.collisionRect(new Rectangle(positionX, positionY, size, size))){
						isCollision = true;
					}
					if(isCollision){
						break;
					}
				}
				for(Obstacle o : obstacles){
					if(o instanceof Cercle){
						Cercle c = (Cercle) o;
						if(c.collision(new Cercle(positionX, positionY, size+20))){
							isCollision = true;
						}
					}
					if(isCollision){
						break;
					}
				}
			}while(fourmiliere.collisionRect(new Rectangle(positionX, positionY, size, size)) || isCollision);
			Cercle c = new Cercle(positionX, positionY, size);
			this.obstacles.add(c);
		}
	}
	
	public void nextStep(){
		for (Fourmi f : fourmiliere.getFourmis()) {
			f.move(fourmiliere, nourritures, pheromones, obstacles);
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
		this.getMonde().getNbPheromones().setText("Phéromones : "+this.getPheromones().size());
		this.getMonde().getQgFood().setText("QG Food : "+fourmiliere.getQuantity());
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

	public HashSet<Pheromone> getPheromones() {
		return pheromones;
	}

	public void setPheromones(HashSet<Pheromone> pheromones) {
		this.pheromones = pheromones;
	}

	public ArrayList<Predator> getPredators() {
		return predators;
	}

	public void setPredators(ArrayList<Predator> predators) {
		this.predators = predators;
	}

	public ArrayList<Obstacle> getObstacles() {
		return obstacles;
	}

	public void setObstacles(ArrayList<Obstacle> obstacles) {
		this.obstacles = obstacles;
	}
	
	
	
}
