package com.fourmis.model;

import java.awt.Graphics;
import java.util.ArrayList;

public interface Insecte {
	public void draw(Graphics g);
	public void move(Fourmiliere fourmiliere, ArrayList<Nourriture> nourritures, ArrayList<Pheromone> pheromones);
}
