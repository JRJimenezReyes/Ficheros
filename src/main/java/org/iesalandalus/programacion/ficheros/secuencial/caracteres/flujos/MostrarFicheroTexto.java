package org.iesalandalus.programacion.ficheros.secuencial.caracteres.flujos;

import java.io.*;

public class MostrarFicheroTexto {

	public static final String FICHERO = "ficheros" + File.separator + "ficheroTexto.txt";
    
	public static void main(String[] args) {

		File ficheroEntrada = new File(FICHERO);
		try (FileReader entrada = new FileReader(ficheroEntrada)){
			int dato;
			while ((dato = entrada.read()) != -1) {
				System.out.print((char) dato);
			}
		} catch (FileNotFoundException e) {
			System.out.println("No se puede leer el fichero de entrada");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida");
		}
	}

}
