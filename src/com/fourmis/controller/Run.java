package com.fourmis.controller;

import java.awt.FontFormatException;
import java.io.IOException;

import com.fourmis.view.Preferences;

/**
 * Point d'entr� de l'application
 *
 */
public class Run {

	public static void main(String[] args) throws FontFormatException, IOException {
		new Preferences();
	}

}
