package com.fourmis.model;



/**
 * se charge de l’affichage des éléments simulés (monde, fourmis,
 * phéromones et fourmilière)
 * @author afidalgo
 */
public class Rendu {
	
	public Rendu(){
		
	}
	
	/**
	 * rafraîchit l’affichage en prenant en compte une nouvelle
	 * configuration de la simulation
	 * @param sim
	 */
	public void paint(Simulation sim){
		sim.getMonde().repaint();
		sim.getMonde().getTerrain().repaint();
		for(Fourmi f : sim.getFourmis())
			f.repaint();
	}
}
