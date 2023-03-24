package org.iesalandalus.programacion.ficheros.file;

import java.io.File;

public class MostrarArbol {

	public static void main(String[] args) {
		File carpeta = new File(".");
		if (!carpeta.exists()) 
			System.out.println(args[0] + " NO existe.");
		else
			imprimeArbol(carpeta, "");
	}

	private static void imprimeArbol(File carpeta, String tabulador) {
		File[] contenido = carpeta.listFiles();
		if (contenido != null) {
			for (int i = 0; i < contenido.length; i++) 
				if (contenido[i].isDirectory()) {
					System.out.println(tabulador + "|-" + contenido[i].getName());
					imprimeArbol(contenido[i], tabulador + "|  ");
				} else {
					System.out.println(tabulador + "+-" + contenido[i].getName());
				}
		}
	}

}
