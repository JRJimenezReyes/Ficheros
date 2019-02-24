package org.iesalandalus.programacion.ficheros.secuencial.bytes.flujos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopiarFicheroBinario {
	
	public static final String FICHERO_ENTRADA = "ficheros" + File.separator + "ficheroBinarioGrande.bin";
	public static final String FICHERO_SALIDA= "ficheros" + File.separator + "SalidaFicheroBinarioGrande.bin";
    
	public static void main(String[] args) {

		File ficheroEntrada = new File(FICHERO_ENTRADA);
		File ficheroSalida = new File(FICHERO_SALIDA);
		
		try (FileInputStream entrada = new FileInputStream(ficheroEntrada)){
			try (FileOutputStream salida = new FileOutputStream(ficheroSalida)){	
				try {
					int dato;
					while ((dato = entrada.read()) != -1) {
						salida.write(dato);
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
