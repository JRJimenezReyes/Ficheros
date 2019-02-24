package org.iesalandalus.programacion.ficheros.file;

import java.io.File;
import java.util.Date;

import utilidades.Entrada;

public class MostrarPropiedades {

	public static void main(String[] args) {
		
		System.out.println("Propiedades del sistema");
		System.out.println("=======================");
		System.out.println("Separador del sistema: " + File.separator);
		System.out.println("Directorio de trabajo: " + new java.io.File("").getAbsolutePath());
		System.out.println("URL de la clase: " + MostrarPropiedades.class.getClassLoader().getResource("").getPath());
		System.out.println("Directorio de trabajo (user.dir): " + System.getProperty("user.dir"));
		System.out.println();
		System.out.print("Escribe el nombre del fichero: ");
		String nombreFichero = Entrada.cadena();
		while(!nombreFichero.equals("FIN")) {
			File fichero = new File(nombreFichero);
			if (fichero.exists()) {
				mostrarPropiedadesFichero(fichero);
			} else {
				System.out.println("El fichero: " + nombreFichero + " NO existe.");
			}
			
			System.out.print("Escribe el nombre del fichero: ");
			nombreFichero = Entrada.cadena();
		}

	}

	private static void mostrarPropiedadesFichero(File fichero) {
		System.out.println("Fecha última modificación: " + new Date(fichero.lastModified()));
		System.out.println("Directorio? " + fichero.isDirectory());
		System.out.println("Fichero? " + fichero.isFile());
		System.out.println("Se puede escribir? " + fichero.canWrite());
		System.out.println("Se puede leer? " + fichero.canRead());
		System.out.println("Se puede ejecutar? " + fichero.canExecute());
		System.out.println("Camino absoluto: " + fichero.getAbsolutePath());
		System.out.println("Tamaño: " + fichero.length());
		
		if (fichero.isDirectory()) {
			System.out.println();
			System.out.println("Contenido del directorio");
			System.out.println("========================");
			File[] ficheros = fichero.listFiles();
			for (int i=0; i<ficheros.length; i++) {
				if (ficheros[i].isDirectory()) {
					System.out.println("D-> " + ficheros[i]);
				} else {
					System.out.println("A-> " + ficheros[i]);
				}
			}
		}
		System.out.println("---------------------------");
		System.out.println();
	}

}
