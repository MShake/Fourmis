package com.fourmis.model;

/**
 * Représente les options rentrée dans l'IHM Preferences. Son but et d'être 
 * envoyé à l'objet Simulation pour son initialisation
 *
 */
public class Options {
	private int nombreFourmis;
	
	public Options(){
		
	}

	public int getNombreFourmis() {
		return nombreFourmis;
	}

	public void setNombreFourmis(int nombreFourmis) {
		this.nombreFourmis = nombreFourmis;
	}
	
	
}
