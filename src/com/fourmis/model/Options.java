package com.fourmis.model;

/**
 * Repr�sente les options rentr�e dans l'IHM Preferences. Son but et d'�tre 
 * envoy� � l'objet Simulation pour son initialisation
 *
 */
public class Options {
	private int sizeScreen;
	private int nombreFourmis;
	private int nombreNourritures;
	private int time;
	private int nombreCoccinelles;
	private int nombreFourmiliers;
	
	public Options(){
		
	}

	public int getNombreFourmis() {
		return nombreFourmis;
	}

	public void setNombreFourmis(int nombreFourmis) {
		this.nombreFourmis = nombreFourmis;
	}
	public int getNombreCoccinelles() {
		return nombreCoccinelles;
	}

	public void setNombreCoccinelles(int nombreCoccinelles) {
		this.nombreCoccinelles = nombreCoccinelles;
	}
	public int getNombreFourmiliers() {
		return nombreFourmiliers;
	}

	public void setNombreFourmiliers(int nombreFourmiliers) {
		this.nombreFourmiliers = nombreFourmiliers;
	}
	public int getSizeScreen() {
		return sizeScreen;
	}

	public void setSizeScreen(int sizeScreen) {
		this.sizeScreen = sizeScreen;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getNombreNourritures() {
		return nombreNourritures;
	}

	public void setNombreNourritures(int nombreNourritures) {
		this.nombreNourritures = nombreNourritures;
	}
	
}
