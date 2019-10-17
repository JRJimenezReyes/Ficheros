package org.iesalandalus.programacion.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.iesalandalus.programacion.ficheros.secuencial.bytes.objetos.Persona;

public class ConvertirCSVAFicheroObjetos {
	
	private static final String FICHERO_OBJETOS = "ficheros" + File.separator + "personas.dat";
	private static final String FICHERO_CSV = "ficheros" + File.separator + "personas.csv";
	
	private static final String SEPARADOR = ",";
	
	public static void main(String[] args) {
		List<Persona> personas = leerCSV(new File(FICHERO_CSV));
		escribirFicheroObjetos(personas, new File(FICHERO_OBJETOS));
	}
	
	private static List<Persona> leerCSV(File fichero) {
		List<Persona> personas = new ArrayList<>();
		try (BufferedReader entrada = new BufferedReader(new FileReader(fichero))){
			String linea;
			while ((linea = entrada.readLine()) != null) {
				String[] campos = linea.split(SEPARADOR);
				personas.add(new Persona(campos[0], Integer.parseInt(campos[1])));
			}
			System.out.println("Fichero CSV leído correctamente.");
		} catch (FileNotFoundException e) {
			System.out.println("No se leer el fichero de entrada");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida");
		}
		return personas;
	}
	
	private static void escribirFicheroObjetos(List<Persona> personas, File fichero) {
		try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(fichero))){
			for (Persona persona : personas) {
				salida.writeObject(persona);
			}
			System.out.println("Fichero de objetos escrito correctamente.");
		} catch (IOException e) {
			System.out.println("No puedo abrir el fihero de salida.");
		}
	}
}
