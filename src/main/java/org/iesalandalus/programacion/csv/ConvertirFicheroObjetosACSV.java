package org.iesalandalus.programacion.csv;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import org.iesalandalus.programacion.ficheros.secuencial.bytes.objetos.Persona;

public class ConvertirFicheroObjetosACSV {
	
	private static final String FICHERO_OBJETOS = "ficheros" + File.separator + "personas.dat";
	private static final String FICHERO_CSV = "ficheros" + File.separator + "personas.csv";
	
	private static final String SEPARADOR = ",";

	public static void main(String[] args) {
		List<Persona> personas = leerFicheroObjetos(new File(FICHERO_OBJETOS));
		escribirFicheroCSV(personas);
	}
	
	private static List<Persona> leerFicheroObjetos(File fichero) {
		List<Persona> personas = new ArrayList<>();
		try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(fichero))){
			leerObjetos(personas, entrada);
		} catch (IOException e) {
			System.out.println("No puedo abrir el fihero de entrada.");
		}
		return personas;
	}

	private static void leerObjetos(List<Persona> personas, ObjectInputStream entrada) {
		try {
			Persona persona;
			while ((persona = (Persona) entrada.readObject()) != null) {
				personas.add(persona);
			}
			System.out.println("Fichero leído satisfactoriamente.");
		} catch (ClassNotFoundException e) {
			System.out.println("No puedo encontrar la clase que tengo que leer.");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida.");
		}
	}
	
	private static void escribirFicheroCSV(List<Persona> personas) {
		File ficheroSalida = new File(FICHERO_CSV);
		try (BufferedWriter salida = new BufferedWriter(new FileWriter(ficheroSalida))){
			for (Persona persona : personas) {
				salida.write(String.format("%s%s%d%n", persona.getNombre(), SEPARADOR, persona.getEdad()));
			}
			System.out.println("Fichero CSV escrito satisfactoriamente.");
		} catch (IOException e) {
			System.out.println("No puedo crear el fichero de salida: " + FICHERO_CSV);
		}
	}

}
