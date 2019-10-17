package org.iesalandalus.programacion.ficheros.secuencial.bytes.objetos;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LeeObjetos {
	
	public static void main(String[] args) {
		File fichero = new File("ficheros/personas.dat");
		try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(fichero))){
			mostrarObjectos(entrada);
		} catch (IOException e) {
			System.out.println("No puedo abrir el fihero de entrada.");
		}
	}

	private static void mostrarObjectos(ObjectInputStream entrada) {
		Persona persona;
		try {
			while ((persona = (Persona) entrada.readObject()) != null) {
				System.out.println("Nombre: " + persona.getNombre() + ", edad: " + persona.getEdad());
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
