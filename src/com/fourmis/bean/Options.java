package com.fourmis.bean;

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
	private int nombreRonds;
	private int speedPheronomones;
	private boolean paintBody;
	
	public Options(){
		
	}

	int getNombreFourmis() {
		return nombreFourmis;
	}

	public void setNombreFourmis(int nombreFourmis) {
		this.nombreFourmis = nombreFourmis;
	}

	int getNombreCoccinelles() {
		return nombreCoccinelles;
	}

	public void setNombreCoccinelles(int nombreCoccinelles) {
		this.nombreCoccinelles = nombreCoccinelles;
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

	int getNombreNourritures() {
		return nombreNourritures;
	}

	public void setNombreNourritures(int nombreNourritures) {
		this.nombreNourritures = nombreNourritures;
	}

	int getNombreRonds() {
		return nombreRonds;
	}

	public void setNombreRonds(int nombreRonds) {
		this.nombreRonds = nombreRonds;
	}

	int getSpeedPheronomones() {
		return speedPheronomones;
	}

	public void setSpeedPheromones(int speedPheronomones) {
		this.speedPheronomones = speedPheronomones;
	}

	boolean isPaintBody() {
		return paintBody;
	}

	public void setPaintBody(boolean paintBody) {
		this.paintBody = paintBody;
	}

	public void setNombreFourmiliers(int value) {
	}
}
