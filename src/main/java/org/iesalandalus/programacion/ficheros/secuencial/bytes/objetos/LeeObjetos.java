package org.iesalandalus.programacion.ficheros.secuencial.bytes.objetos;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LeeObjetos {
	
	private static final String FICHERO = String.format("%s%s%s", "ficheros", File.separator, "personas.dat");
	
	public static void main(String[] args) {
		try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(new File(FICHERO)))){
			mostrarObjectos(entrada);
		} catch (IOException e) {
			System.out.println("No puedo abrir el fihero de entrada.");
		}
	}

	private static void mostrarObjectos(ObjectInputStream entrada) {
		Persona persona;
		try {
			while ((persona = (Persona) entrada.readObject()) != null) {
				System.out.printf("Nombre: %s, edad: %d%n", persona.getNombre(), persona.getEdad());
			}
		} catch (EOFException eo) {
			System.out.println("Fichero leído satisfactoriamente.");
		} catch (ClassNotFoundException e) {
			System.out.println("No puedo encontrar la clase que tengo que leer.");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida.");
		}
	}

}
