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
	private Container container = new Container();
	private JPanel header = new JPanel();
	private JPanel body = new JPanel();
	private JPanel footer = new JPanel();
	
	private JLabel title = new JLabel("Fourmis Simulator", JLabel.CENTER);
	private JLabel sousTitle = new JLabel("Phéromones Edition ®", JLabel.CENTER);

	public Preferences() throws FontFormatException, IOException{
		options = new Options();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Fourmis Simulator 2014 Phéromones Edition ®");
		this.setSize(500, 417);
		this.setLocation(100, 100);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image favicon = kit.getImage("res/img/favicon.png");
		this.setIconImage(favicon);

		Font font = Font.createFont(Font.TRUETYPE_FONT, new File("res/font/buglife.ttf"));   
		GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
		genv.registerFont(font);
		font = font.deriveFont(40f);
		Font italic = new Font(sousTitle.getFont().getName(),Font.ITALIC,sousTitle.getFont().getSize()); 
		
		this.container.setLayout(new BorderLayout());
		this.header.setLayout(new GridLayout(0,1));
		
		this.title.setFont(font);
		this.sousTitle.setFont(italic);
		this.sousTitle.setVerticalAlignment(1);
		this.header.add(title);
		this.header.add(sousTitle);
		
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
			options.setNombreFourmis(10);
			controleur = new Controleur(options);
			this.start.setEnabled(true);
			this.generate.setEnabled(false);
		}
		if(e.getSource() == start){
			controleur.run();
		}
		
	}
	
}
