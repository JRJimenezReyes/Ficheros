package org.iesalandalus.programacion.ficheros.secuencial.caracteres.flujos;

import java.io.*;

public class EscribirFicheroTexto {
	
	public static final String FICHERO = "ficheros" + File.separator + "SalidaFicheroTexto.txt";
    
	public static void main(String[] args) {

		File ficheroSalida = new File(FICHERO);
		try (FileWriter salida = new FileWriter(ficheroSalida);
			InputStreamReader entrada = new InputStreamReader(System.in)){
			try {
				int dato;
				while ((dato = entrada.read()) != '|') {
					salida.write((char)dato);
				}
			} catch (IOException e) {
				System.out.println("Error inesperado de Entrada/Salida");
			}
		}
		catch (IOException e) {
			System.out.println("No se puede crear el fichero de salida");
		}
	}

}
