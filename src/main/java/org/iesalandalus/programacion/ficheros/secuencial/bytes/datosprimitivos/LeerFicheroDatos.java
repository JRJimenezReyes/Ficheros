package org.iesalandalus.programacion.ficheros.secuencial.bytes.datosprimitivos;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class LeerFicheroDatos {
	
	private static final String FICHERO = String.format("%s%s%s", "ficheros", File.separator, "ficheroDatos.bin");
    
	public static void main(String[] args) {		
		try (DataInputStream entrada = new DataInputStream(new FileInputStream(new File(FICHERO)))){
			mostrarDatos(entrada);
		} catch (IOException e) {
			System.out.printf("No existe el fichero de origen: %s%n", FICHERO);
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
				System.out.printf("Cadena: %s\tEntero: %s\tDoble: %s%n", datoString, datoInt, datoDouble);
			}
		} catch (EOFException e) {
			System.out.println("Fichero leído satisfactoriamente.");
		} catch (IOException e) {
			System.out.println("Error inesperado de E/S.");
		}
	}

}
