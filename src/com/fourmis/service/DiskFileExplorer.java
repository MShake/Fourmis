package com.fourmis.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class DiskFileExplorer {

/**
 * Constructeur
 * @param path chemin du répertoire
 * @param subFolder analyse des sous dossiers
 */
    public DiskFileExplorer() {
        super();
    }

    public ArrayList<String> list() {
        return this.listDirectory();
    }

    private ArrayList<String> listDirectory() {

    	File curDir = new File("./src");
    	File[] filesList = curDir.listFiles();
    	ArrayList<String> saveFiles = new ArrayList<String>();
		for(File f : Objects.requireNonNull(filesList)){
			if(f.isFile() && f.getName().endsWith(".properties"))
				saveFiles.add(f.getName().substring(0, f.getName().lastIndexOf('.')));
		}
		return saveFiles;
    }

  
}