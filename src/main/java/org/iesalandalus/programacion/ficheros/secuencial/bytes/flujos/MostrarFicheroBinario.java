package org.iesalandalus.programacion.ficheros.secuencial.bytes.flujos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MostrarFicheroBinario {
	public static final String FICHERO = "ficheros" + File.separator + "ficheroBinarioPeque.bin";
    
	public static void main(String[] args) {

		File ficheroEntrada = new File(FICHERO);
		try (FileInputStream entrada = new FileInputStream(ficheroEntrada)){
			int dato, numBytes = 0, desplazamiento = 0;
			StringBuilder lineaHexa = new StringBuilder(""), lineaChar = new StringBuilder("");
			while ((dato = entrada.read()) != -1) {
				numBytes++;
				lineaHexa.append(String.format("%02x", dato) + " ");
				lineaChar.append((32 <= dato && 126 >= dato) ? (char)dato : ".");
				if (numBytes == 8)
					lineaHexa.append(" ");
				if (numBytes == 16) {
					System.out.println(String.format("%08x %s |%s|",  desplazamiento, lineaHexa, lineaChar));
					numBytes = 0;
					desplazamiento += 16;
					lineaHexa.delete(0, lineaHexa.capacity());
					lineaChar.delete(0, lineaChar.capacity());
				}
			}
			System.out.println(String.format("%08x %-49s |%-16s|",  desplazamiento, lineaHexa, lineaChar));
		} catch (FileNotFoundException e) {
			System.out.println("No se leer el fichero de entrada");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida");
		}
	}
}
