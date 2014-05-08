package com.fourmis.model;

/**
 * Repr�sente les options rentr�e dans l'IHM Preferences. Son but et d'�tre 
 * envoy� � l'objet Simulation pour son initialisation
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
