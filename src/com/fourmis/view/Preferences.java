package com.fourmis.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.fourmis.controller.Controleur;
import com.fourmis.model.Options;

/**
 * Représente l'IHM de settings de future simulation
 *
 */
public class Preferences extends JFrame implements ActionListener, ChangeListener{
	
	private Controleur controleur;
	private Options options;
	private JButton generate;
	private JButton start;
	private Container container = new Container();
	private JPanel header = new JPanel();
	private JPanel body = new JPanel();
	private JPanel footer = new JPanel();
	
	private JLabel title = new JLabel("Fourmis Simulator", JLabel.CENTER);
	private JLabel sousTitle = new JLabel("Pheromones & Predators Edition ®", JLabel.CENTER);
	private JLabel sizeScreen = new JLabel("Taille du terrain (500) :", JLabel.CENTER);
	private JLabel numberFourmis = new JLabel("Nombre de fourmis (50) :", JLabel.CENTER);
	private JLabel time = new JLabel("Temps (50) :", JLabel.CENTER);
	private JLabel numberFood = new JLabel("Nombre de nourritures (1) :", JLabel.CENTER);
	
	private JSlider slideSizeScreen = new JSlider(300, 900, 500);
	private JSlider slideNumberFourmis = new JSlider(10, 200, 50);
	private JSlider slideTime = new JSlider(1, 100, 50);
	private JSlider slideNumberFood = new JSlider(1, 20, 1);

	public Preferences() throws FontFormatException, IOException{
		options = new Options();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Fourmis Simulator 2014 Phéromones & Predators Edition ®");
		this.setSize(500, 417);
		this.setLocation(100, 100);
		this.setResizable(false);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image favicon = kit.getImage("res/img/favicon.png");
		this.setIconImage(favicon);

		Font font = Font.createFont(Font.TRUETYPE_FONT, new File("res/font/buglife.ttf"));   
		GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
		genv.registerFont(font);
		font = font.deriveFont(40f);
		Font italic = new Font(sousTitle.getFont().getName(),Font.ITALIC,sousTitle.getFont().getSize()); 
		
		slideSizeScreen.setOpaque(false);
		slideSizeScreen.addChangeListener(this);
		slideNumberFourmis.setOpaque(false);
		slideNumberFourmis.addChangeListener(this);
		slideTime.setOpaque(false);
		slideTime.addChangeListener(this);
		slideNumberFood.setOpaque(false);
		slideNumberFood.addChangeListener(this);
		
		this.container.setLayout(new BorderLayout());
		this.header.setLayout(new GridLayout(0,1));
		this.body.setLayout(new GridLayout(4, 2));
		
		this.title.setFont(font);
		this.sousTitle.setFont(italic);
		this.sousTitle.setVerticalAlignment(1);
		this.header.add(title);
		this.header.add(sousTitle);
		
		this.body.add(sizeScreen);
		this.body.add(slideSizeScreen);
		this.body.add(numberFourmis);
		this.body.add(slideNumberFourmis);
		this.body.add(time);
		this.body.add(slideTime);
		this.body.add(numberFood);
		this.body.add(slideNumberFood);
		
		generate = new JButton("Generate");
		generate.addActionListener(this);
		footer.add(generate);
		
		start = new JButton("Start", new ImageIcon(favicon));
		start.setEnabled(false);
		start.addActionListener(this);
		footer.add(start);
		
		this.container.add(header, BorderLayout.NORTH);
		this.container.add(body, BorderLayout.CENTER);
		this.container.add(footer, BorderLayout.SOUTH);
		this.setContentPane(container);
		
		this.header.setOpaque(false);
		this.body.setOpaque(false);
		this.footer.setOpaque(false);
		this.setVisible(true); 
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == generate){
			options.setSizeScreen(slideSizeScreen.getValue());
			options.setNombreFourmis(slideNumberFourmis.getValue());
			options.setNombreNourritures(slideNumberFood.getValue());
			options.setTime(slideTime.getValue());
			controleur = new Controleur(options);
			this.start.setEnabled(true);
			this.generate.setEnabled(false);
		}
		if(e.getSource() == start){
			controleur.run();
		}
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if(e.getSource() == slideSizeScreen){
			this.sizeScreen.setText("Taille du terrain ("+slideSizeScreen.getValue()+") :");
		}else if(e.getSource() == slideNumberFourmis){
			this.numberFourmis.setText("Nombre de fourmis ("+slideNumberFourmis.getValue()+") :");
		}else if(e.getSource() == slideTime){
			this.time.setText("Temps ("+slideTime.getValue()+") :");
		}else if(e.getSource() == slideNumberFood){
			this.numberFood.setText("Nombre de nourritures ("+slideNumberFood.getValue()+") :");
		}
		
	}
	
}
