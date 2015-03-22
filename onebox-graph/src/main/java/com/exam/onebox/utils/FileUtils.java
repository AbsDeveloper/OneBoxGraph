package com.exam.onebox.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class FileUtils {

	private static final Logger log = Logger.getLogger( FileUtils.class.getName() );


	public static BufferedReader getFileInputStream(String pNombreFichero) throws FileNotFoundException {
		FileInputStream fileStream = null;
		BufferedReader reader = null;
		try {
			fileStream = new FileInputStream(pNombreFichero);
			reader = new BufferedReader(new InputStreamReader(fileStream));
		} catch (FileNotFoundException e) {
			FileUtils.log.severe(pNombreFichero + ": Fichero no encontrado");
			throw e;
		}
		return reader;
	}

}
