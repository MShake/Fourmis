package com.fourmis.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.fourmis.controller.Controleur;
import com.fourmis.model.Options;

/**
 * Représente l'IHM de settings de future simulation
 * @author afidalgo
 *
 */
public class Preferences extends JFrame implements ActionListener{
	
	private Controleur controleur;
	private Options options;
	private JButton generate;
	private JButton start;
	private Container container;

	public Preferences(){
		options = new Options();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Fourmis : Préférences");
		this.setSize(260, 75);
		this.setLocation(100, 100);
		
		this.container = new Container();
		this.setContentPane(container);
		
		generate = new JButton("generate");
		generate.addActionListener(this);
		container.add(generate);
		
		start = new JButton("start");
		start.addActionListener(this);
		container.add(start);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == generate){
			options.setNombreFourmis(10);
			controleur = new Controleur(options); 
			
		}
		if(e.getSource() == start){
			controleur.run();
		}
		
	}
}
