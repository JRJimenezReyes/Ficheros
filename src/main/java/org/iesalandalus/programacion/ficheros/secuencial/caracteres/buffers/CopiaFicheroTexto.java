package org.iesalandalus.programacion.ficheros.secuencial.caracteres.buffers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopiaFicheroTexto {

	public static final String FICHERO_ENTRADA = "ficheros" + File.separator + "ficheroTextoGrande.txt";
	public static final String FICHERO_SALIDA= "ficheros" + File.separator + "SalidaFicheroTexto.txt";
    
	public static void main(String[] args) {

		File ficheroEntrada = new File(FICHERO_ENTRADA);
		File ficheroSalida = new File(FICHERO_SALIDA);
		
		try (BufferedReader entrada = new BufferedReader(new FileReader(ficheroEntrada))){
			try (BufferedWriter salida = new BufferedWriter(new FileWriter(ficheroSalida))){
				try {
					String linea;
					while ((linea = entrada.readLine()) != null) {
						salida.write(linea);
						salida.newLine();
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
