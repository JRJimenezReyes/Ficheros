package org.iesalandalus.programacion.ficheros.secuencial.bytes.objetos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class EscribeObjetosProblema2 {
	
	private static final String FICHERO = String.format("%s%s%s", "ficheros", File.separator, "personas.dat");

	public static void main(String[] args) {
		Persona persona;
		String[] nombres = { "Juan", "Alfonso", "Araceli", "Manolo", "Rubén", "Elvira", "Inés", "José Ramón" };
		int[] edades = { 31, 35, 25, 40, 37, 18, 20, 43 };
		File fichero = new File(FICHERO);
		try {
			//ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(fichero));
			//La primera vez creamos un objeto ObjectOutputStream que escribirá la cabecera adecuada
			ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(fichero));
			for (int i = 0; i < edades.length; i++) {
				persona = new Persona(nombres[i], edades[i]);
				salida.writeObject(persona);
			}
			salida.close();
			//Al cerrar el flujo y volverlo abrir para añadir, se añade entre medias una cabecera que luego
			//hará que nos salte una excepción al leerlo. Para evitarlo hemos de sobreescribir el método encargado de escribir la
			//cabecera de la clase ObjectOutputStream (writeStreamHeader).
			//salida = new ObjectOutputStream(new FileOutputStream(fichero, true));
			//Como ahora abrimos para añadir, creamos un objeto de nuestra clase heredada que no escribe cabecera
			//salida = new ObjectOutputStream(new FileOutputStream(fichero, true));
			salida = new MyObjectOutputStream(new FileOutputStream(fichero, true));
			for (int i = 0; i < edades.length; i++) {
				persona = new Persona(nombres[i], edades[i]);
				salida.writeObject(persona);
			}
			salida.close();
			System.out.println("Fichero escrito satisfactoriamente");
		} catch (FileNotFoundException e) {
			System.out.println("No puedo crear el fichero de salida");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida");
		}
	}
}
