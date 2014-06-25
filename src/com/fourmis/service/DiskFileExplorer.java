package com.fourmis.service;

import java.io.File;
import java.util.ArrayList;

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
        ArrayList<String> saveFiles = this.listDirectory();
        return saveFiles;
    }

    private ArrayList<String> listDirectory() {

    	File curDir = new File("./src");
    	File[] filesList = curDir.listFiles();
    	ArrayList<String> saveFiles = new ArrayList<String>();
		for(File f : filesList){
			if(f.isFile()){
				saveFiles.add(f.getName());
			}
		}
		return saveFiles;
    }

  
}