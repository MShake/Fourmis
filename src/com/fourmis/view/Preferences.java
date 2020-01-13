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
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.fourmis.bean.Options;
import com.fourmis.controller.Controleur;
import com.fourmis.service.DiskFileExplorer;
import com.fourmis.service.RecoveryData;

/**
 * Représente l'IHM de settings de future simulation
 *
 */
public class Preferences extends JFrame implements ActionListener, ChangeListener{
	
	private Controleur controleur;
	private Options options;
	private JButton generate;
	private JButton start;

	private JMenuItem save = new JMenuItem("Sauvegarder");

	private JLabel sizeScreen = new JLabel("Taille du terrain (500) :", JLabel.CENTER);
	private JLabel numberFourmis = new JLabel("Nombre de fourmis (50) :", JLabel.CENTER);
	private JLabel numberCoccinelles = new JLabel("Nombre de coccinelles (0) :", JLabel.CENTER);
	private JLabel numberFourmiliers = new JLabel("Nombre de fourmiliers (0) :", JLabel.CENTER);
	private JLabel numberRonds = new JLabel("Nombre de ronds (0) :", JLabel.CENTER);
	private JLabel time = new JLabel("Temps (50) :", JLabel.CENTER);
	private JLabel numberFood = new JLabel("Nombre de nourritures (1) :", JLabel.CENTER);
	private JLabel speedPheromones = new JLabel("Taux d'évaporation (1) :", JLabel.CENTER);

	private JSlider slideSizeScreen = new JSlider(300, 900, 500);
	private JSlider slideNumberFourmis = new JSlider(10, 200, 50);
	private JSlider slideNumberCoccinelles = new JSlider(0, 10, 0);
	private JSlider slideNumberFourmiliers = new JSlider(0, 10, 0);
	private JSlider slideNumberRonds = new JSlider(0, 5, 0);
	private JSlider slideTime = new JSlider(1, 100, 50);
	private JSlider slideNumberFood = new JSlider(1, 20, 1);
	private JSlider slideSpeedPheromones = new JSlider(1, 20, 1);
	
	private JCheckBox checkPaintBody = new JCheckBox();
	
	private ArrayList<String> saveFiles;

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
		
		DiskFileExplorer diskFileExplorer = new DiskFileExplorer();
        saveFiles = diskFileExplorer.list();

		JMenu load = new JMenu("Charger");
		for(String file : saveFiles) {
        	JMenuItem fileItem = new JMenuItem(file);
        	fileItem.addActionListener(e -> {
				JMenuItem fileItem1 = (JMenuItem) e.getSource();
				RecoveryData datas = new RecoveryData(fileItem1.getText()+".properties");
				slideSizeScreen.setValue(datas.getAllData().getOrDefault("sizeScreen", 300));
				sizeScreen.setText("Taille du terrain ("+slideSizeScreen.getValue()+") :");

				slideNumberFourmis.setValue(datas.getAllData().getOrDefault("numberFourmis", 10));
				numberFourmis.setText("Nombre de fourmis ("+slideNumberFourmis.getValue()+") :");

				slideTime.setValue(datas.getAllData().getOrDefault("time", 1));
				time.setText("Temps ("+slideTime.getValue()+") :");

				slideNumberFood.setValue(datas.getAllData().getOrDefault("numberFood", 1));
				numberFood.setText("Nombre de nourritures ("+slideNumberFood.getValue()+") :");

				slideSpeedPheromones.setValue(datas.getAllData().getOrDefault("speedPheromones", 1));
				speedPheromones.setText("Taux d'évaporation ("+slideSpeedPheromones.getValue()+") :");

				slideNumberCoccinelles.setValue(datas.getAllData().getOrDefault("numberCoccinelles", 0));
				numberCoccinelles.setText("Nombre de coccinelles ("+slideNumberCoccinelles.getValue()+") :");

				slideNumberFourmiliers.setValue(datas.getAllData().getOrDefault("numberFourmiliers", 0));
				numberFourmiliers.setText("Nombre de fourmiliers ("+slideNumberFourmiliers.getValue()+") :");

				slideNumberRonds.setValue(datas.getAllData().getOrDefault("numberRonds", 0));
				numberRonds.setText("Nombre de ronds ("+slideNumberRonds.getValue()+") :");

				if(datas.getAllData().containsKey("paintBody")){
					if(datas.getAllData().get("paintBody") == 1){
						checkPaintBody.setSelected(true);
					}else{
						checkPaintBody.setSelected(false);
					}
				}else{
					checkPaintBody.setSelected(false);
				}
			});
        	load.add(fileItem);
        }
		
        this.save.addActionListener(this);

		JMenu parametre = new JMenu("Paramètres");
		parametre.add(load);
		parametre.add(save);
		JMenuBar menu = new JMenuBar();
		menu.add(parametre);
		this.setJMenuBar(menu);
        
		Font font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/com/fourmis/ressources/font/buglife.ttf"));
		GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
		genv.registerFont(font);
		font = font.deriveFont(40f);
		JLabel sousTitle = new JLabel("Pheromones & Predators Edition �", JLabel.CENTER);
		Font italic = new Font(sousTitle.getFont().getName(),Font.BOLD, sousTitle.getFont().getSize());
		
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

		Container container = new Container();
		container.setLayout(new BorderLayout());
		JPanel header = new JPanel();
		header.setLayout(new GridLayout(0,1));

		JLabel title = new JLabel("Fourmis Simulator", JLabel.CENTER);
		title.setFont(font);
		sousTitle.setFont(italic);
		sousTitle.setVerticalAlignment(SwingConstants.TOP);
		header.add(title);
		header.add(sousTitle);

		JPanel oTerrain = new JPanel(new GridLayout(3, 2));
		JTabbedPane onglets = new JTabbedPane(SwingConstants.TOP);
		onglets.addTab("Terrain", oTerrain);
		JPanel oFourmis = new JPanel(new GridLayout(2, 2));
		onglets.addTab("Fourmis", oFourmis);
		JPanel oPheromones = new JPanel(new GridLayout(1, 2));
		onglets.addTab("Phéromones", oPheromones);
		JPanel oPredators = new JPanel(new GridLayout(2, 2));
		onglets.addTab("Prédators", oPredators);
		JPanel oObstacles = new JPanel(new GridLayout(1, 2));
		onglets.addTab("Obstacles", oObstacles);
		onglets.setOpaque(true);
		
		oTerrain.add(sizeScreen);
		oTerrain.add(slideSizeScreen);
		oTerrain.add(time);
		oTerrain.add(slideTime);
		oTerrain.add(numberFood);
		oTerrain.add(slideNumberFood);
		oFourmis.add(numberFourmis);
		oFourmis.add(slideNumberFourmis);
		JLabel paintBody = new JLabel("Peindre le corps ? ", JLabel.CENTER);
		oFourmis.add(paintBody);
		oFourmis.add(checkPaintBody);
		oPheromones.add(speedPheromones);
		oPheromones.add(slideSpeedPheromones);
		oPredators.add(numberCoccinelles);
		oPredators.add(slideNumberCoccinelles);
		oPredators.add(numberFourmiliers);
		oPredators.add(slideNumberFourmiliers);
		oObstacles.add(numberRonds);
		oObstacles.add(slideNumberRonds);

		JPanel body = new JPanel();
		body.add(onglets);
		
		generate = new JButton("Generate");
		generate.addActionListener(this);
		JPanel footer = new JPanel();
		footer.add(generate);
		
		start = new JButton("Start", new ImageIcon(favicon));
		start.setEnabled(false);
		start.addActionListener(this);
		footer.add(start);
		
		container.add(header, BorderLayout.NORTH);
		container.add(body, BorderLayout.CENTER);
		container.add(footer, BorderLayout.SOUTH);
		this.setContentPane(container);
		
		header.setOpaque(false);
		body.setOpaque(true);
		footer.setOpaque(false);
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
			options.setSpeedPheromones(slideSpeedPheromones.getValue());
			options.setTime(slideTime.getValue());
			options.setPaintBody(checkPaintBody.isSelected());
			controleur = new Controleur(options);
			this.start.setEnabled(true);
			this.generate.setEnabled(false);
		}
		if(e.getSource() == start){
			controleur.run();
		}
		
		if(e.getSource() == save){
		    String nom = JOptionPane.showInputDialog(null, "Saisissez un nom pour sauvegarder votre fichier", "Sauvegarde des paramètres !", JOptionPane.QUESTION_MESSAGE);
		    boolean replaceFile = false;
		    if(this.saveFiles.contains(nom)){
		    	int option = JOptionPane.showConfirmDialog(null, "Ce fichier existe déjà, souhaitez-vous le remplacer ?", "Fichier Existant", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		    	if(option == JOptionPane.OK_OPTION){
		    		replaceFile = true;
		    	}
		    }
		    if(!this.saveFiles.contains(nom) || (this.saveFiles.contains(nom) && replaceFile)){
		    	File file = new File("./src/"+nom+".properties");
		    	if(replaceFile)
		    		file.delete();

		    	
		    	FileOutputStream fos;
		    	
		    	try {
					file.createNewFile();
					fos = new FileOutputStream(file);
					
					String values = "sizeScreen = "+slideSizeScreen.getValue();
					values += "\nnumberFourmis = "+slideNumberFourmis.getValue();
					values += "\ntime = "+slideTime.getValue();
					values += "\nnumberFood = "+slideNumberFood.getValue();
					values += "\nspeedPheromones = "+slideSpeedPheromones.getValue();
					values += "\nnumberCoccinelles = "+slideNumberCoccinelles.getValue();
					values += "\nnumberFourmiliers = "+slideNumberFourmiliers.getValue();
					values += "\nnumberRonds = "+slideNumberRonds.getValue();
					if (checkPaintBody.isSelected())
						values += "\npaintBody = 1";
					else
						values += "\npaintBody = 0";
					
					byte[] contentInBytes = values.getBytes();
					fos.write(contentInBytes);
					fos.flush();
					fos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		    
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
