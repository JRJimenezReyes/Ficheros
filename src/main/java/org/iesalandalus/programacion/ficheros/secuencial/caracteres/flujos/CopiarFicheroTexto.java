package org.iesalandalus.programacion.ficheros.secuencial.caracteres.flujos;

import java.io.*;

public class CopiarFicheroTexto {
	
	public static final String FICHERO_ENTRADA = "ficheros" + File.separator + "ficheroTextoGrande.txt";
	public static final String FICHERO_SALIDA= "ficheros" + File.separator + "SalidaFicheroTexto.txt";
    
	public static void main(String[] args) {

		File ficheroEntrada = new File(FICHERO_ENTRADA);
		File ficheroSalida = new File(FICHERO_SALIDA);
		
		try (FileReader entrada = new FileReader(ficheroEntrada)){
			try (FileWriter salida = new FileWriter(ficheroSalida)){	
				try {
					int dato;
					while ((dato = entrada.read()) != -1) {
						salida.write((char)dato);
					}
					System.out.println("Fichero copiado satisfactoriamente");
				} catch (IOException e) {
					System.out.println("Error inesperado de Entrada/Salida");
				}
			} catch (IOException e) {
				System.out.println("No puedo crear el fichero de salida: " + FICHERO_SALIDA);
			}
		} catch (IOException e) {
			System.out.println("No existe el fichero de origen: " + FICHERO_ENTRADA);
		}
	}

}
