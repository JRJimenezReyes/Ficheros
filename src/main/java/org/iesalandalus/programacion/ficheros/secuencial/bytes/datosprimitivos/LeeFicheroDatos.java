package org.iesalandalus.programacion.ficheros.secuencial.bytes.datosprimitivos;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class LeeFicheroDatos {
	
	public static final String FICHERO = "ficheros" + File.separator + "ficheroDatos.bin";
    
	public static void main(String[] args) {

		File fichero = new File(FICHERO);
		
		try (DataInputStream entrada = new DataInputStream(new FileInputStream(fichero))){
			mostrarDatos(entrada);
		} catch (IOException e) {
			System.out.println("No existe el fichero de origen: " + FICHERO);
		}
	}

	private static void mostrarDatos(DataInputStream entrada) {
		try {
			String datoString = "";
			int datoInt;
			double datoDouble;
			while (datoString != null) { //Esta condición siempre será verdadera
				datoString = entrada.readUTF();
				datoInt = entrada.readInt();
				datoDouble = entrada.readDouble();
				System.out.println("Cadena: " + datoString + "\tEntero: " + datoInt + "\tDoble: " + datoDouble);
			}
		} catch (EOFException e) {
			System.out.println("Fichero leído satisfactoriamente.");
		} catch (IOException e) {
			System.out.println("Error inesperado de E/S.");
		}
	}

}
