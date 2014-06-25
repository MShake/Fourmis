package com.fourmis.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Frame;
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
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.fourmis.bean.Options;
import com.fourmis.controller.Controleur;

/**
 * Repr�sente l'IHM de settings de future simulation
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
	private JPanel oTerrain = new JPanel(new GridLayout(3, 2));
	private JPanel oFourmis = new JPanel(new GridLayout(2, 2));
	private JPanel oPheromones = new JPanel(new GridLayout(1, 2));
	private JPanel oPredators = new JPanel(new GridLayout(2, 2));
	private JPanel oObstacles = new JPanel(new GridLayout(1, 2));
	private JTabbedPane onglets = new JTabbedPane(SwingConstants.TOP);
	
	private JMenuBar menu = new JMenuBar();
	private JMenu parametre = new JMenu("Paramètres");
	private JMenu load = new JMenu("Charger");
	private JMenuItem save = new JMenuItem("Sauvegarder");
	
	private JLabel title = new JLabel("Fourmis Simulator", JLabel.CENTER);
	private JLabel sousTitle = new JLabel("Pheromones & Predators Edition �", JLabel.CENTER);
	private JLabel sizeScreen = new JLabel("Taille du terrain (500) :", JLabel.CENTER);
	private JLabel numberFourmis = new JLabel("Nombre de fourmis (50) :", JLabel.CENTER);
	private JLabel numberCoccinelles = new JLabel("Nombre de coccinelles (0) :", JLabel.CENTER);
	private JLabel numberFourmiliers = new JLabel("Nombre de fourmiliers (0) :", JLabel.CENTER);
	private JLabel numberRonds = new JLabel("Nombre de ronds (0) :", JLabel.CENTER);
	private JLabel time = new JLabel("Temps (50) :", JLabel.CENTER);
	private JLabel numberFood = new JLabel("Nombre de nourritures (1) :", JLabel.CENTER);
	private JLabel speedPheromones = new JLabel("Taux d'évaporation (1) :", JLabel.CENTER);
	private JLabel paintBody = new JLabel("Peindre le corps ? ", JLabel.CENTER);
	
	private JSlider slideSizeScreen = new JSlider(300, 900, 500);
	private JSlider slideNumberFourmis = new JSlider(10, 200, 50);
	private JSlider slideNumberCoccinelles = new JSlider(0, 10, 0);
	private JSlider slideNumberFourmiliers = new JSlider(0, 10, 0);
	private JSlider slideNumberRonds = new JSlider(0, 5, 0);
	private JSlider slideTime = new JSlider(1, 100, 50);
	private JSlider slideNumberFood = new JSlider(1, 20, 1);
	private JSlider slideSpeedPheromones = new JSlider(1, 20, 1);
	
	private JCheckBox checkPaintBody = new JCheckBox();

	public Preferences() throws FontFormatException, IOException{
		options = new Options();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Fourmis Simulator 2014 Phéromones & Predators Edition ®");
		this.setSize(500, 417);
		this.setLocation(100, 100);
		this.setResizable(false);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image favicon = kit.getImage(Frame.class.getResource("/com/fourmis/ressources/img/favicon.png"));
		this.setIconImage(favicon);
		
		this.parametre.add(load);
		this.parametre.add(save);
		this.menu.add(parametre);
		this.setJMenuBar(menu);
		
		Font font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/com/fourmis/ressources/font/buglife.ttf"));
		GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
		genv.registerFont(font);
		font = font.deriveFont(40f);
		Font italic = new Font(sousTitle.getFont().getName(),Font.BOLD,sousTitle.getFont().getSize()); 
		
		slideSizeScreen.setOpaque(false);
		slideSizeScreen.addChangeListener(this);
		slideNumberFourmis.setOpaque(false);
		slideNumberFourmis.addChangeListener(this);
		slideNumberCoccinelles.setOpaque(false);
		slideNumberCoccinelles.addChangeListener(this);
		slideNumberFourmiliers.setOpaque(false);
		slideNumberFourmiliers.addChangeListener(this);
		slideTime.setOpaque(false);
		slideTime.addChangeListener(this);
		slideNumberFood.setOpaque(false);
		slideNumberFood.addChangeListener(this);
		slideSpeedPheromones.setOpaque(false);
		slideSpeedPheromones.addChangeListener(this);
		slideNumberRonds.setOpaque(false);
		slideNumberRonds.addChangeListener(this);
		
		this.container.setLayout(new BorderLayout());
		this.header.setLayout(new GridLayout(0,1));
		
		this.title.setFont(font);
		this.sousTitle.setFont(italic);
		this.sousTitle.setVerticalAlignment(1);
		this.header.add(title);
		this.header.add(sousTitle);
		
		onglets.addTab("Terrain", oTerrain);
		onglets.addTab("Fourmis", oFourmis);
		onglets.addTab("Phéromones", oPheromones);
		onglets.addTab("Prédators", oPredators);
		onglets.addTab("Obstacles", oObstacles);
		onglets.setOpaque(true);
		
		this.oTerrain.add(sizeScreen);
		this.oTerrain.add(slideSizeScreen);
		this.oTerrain.add(time);
		this.oTerrain.add(slideTime);
		this.oTerrain.add(numberFood);
		this.oTerrain.add(slideNumberFood);
		this.oFourmis.add(numberFourmis);
		this.oFourmis.add(slideNumberFourmis);
		this.oFourmis.add(paintBody);
		this.oFourmis.add(checkPaintBody);
		this.oPheromones.add(speedPheromones);
		this.oPheromones.add(slideSpeedPheromones);
		this.oPredators.add(numberCoccinelles);
		this.oPredators.add(slideNumberCoccinelles);
		this.oPredators.add(numberFourmiliers);
		this.oPredators.add(slideNumberFourmiliers);
		this.oObstacles.add(numberRonds);
		this.oObstacles.add(slideNumberRonds);
		
		this.body.add(onglets);		
		
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
		this.body.setOpaque(true);
		this.footer.setOpaque(false);
		this.setVisible(true); 
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == generate){
			options.setSizeScreen(slideSizeScreen.getValue());
			options.setNombreFourmis(slideNumberFourmis.getValue());
			options.setNombreCoccinelles(slideNumberCoccinelles.getValue());
			options.setNombreFourmiliers(slideNumberFourmiliers.getValue());
			options.setNombreNourritures(slideNumberFood.getValue());
			options.setNombreRonds(slideNumberRonds.getValue());
			options.setTime(slideTime.getValue());
			options.setPaintBody(checkPaintBody.isSelected());
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
		else if(e.getSource() == slideSpeedPheromones){
		this.speedPheromones.setText("Taux d'évaporation ("+slideSpeedPheromones.getValue()+") :");
	}
		else if(e.getSource() == slideNumberCoccinelles){
			this.numberCoccinelles.setText("Nombre de coccinelles ("+slideNumberCoccinelles.getValue()+") :");
		}
		else if(e.getSource() == slideNumberFourmiliers){
			this.numberFourmiliers.setText("Nombre de fourmiliers ("+slideNumberFourmiliers.getValue()+") :");
		}
		else if(e.getSource() == slideNumberRonds){
			this.numberRonds.setText("Nombre de ronds ("+slideNumberRonds.getValue()+") :");
		}
		
	}
	
}
