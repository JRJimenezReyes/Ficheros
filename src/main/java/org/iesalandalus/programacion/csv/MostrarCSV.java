package org.iesalandalus.programacion.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MostrarCSV {
	
	public static final String FICHERO_CSV = String.format("%s%s%s", "ficheros", File.separator, "personas.csv");

	public static void main(String[] args) {
		try (BufferedReader entrada = new BufferedReader(new FileReader(FICHERO_CSV))){
			String linea;
			while ((linea = entrada.readLine()) != null) {
				System.out.println(linea);
			}
		} catch (FileNotFoundException e) {
			System.out.printf("No se puede leer el fichero de entrada: %s.%n", FICHERO_CSV);
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida");
		}
	}

}
