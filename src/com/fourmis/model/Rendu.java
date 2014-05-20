package com.fourmis.model;



/**
 * se charge de l�affichage des �l�ments simul�s (monde, fourmis,
 * ph�romones et fourmili�re)
 * 
 */
public class Rendu {
	
	public Rendu(){
		
	}
	
	/**
	 * rafra�chit l�affichage en prenant en compte une nouvelle
	 * configuration de la simulation
	 * @param sim
	 */
	public void paint(Simulation sim){
		sim.getMonde().repaint();
		sim.getMonde().getTerrain().repaint();
	}
}
