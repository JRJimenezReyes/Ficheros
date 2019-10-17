package org.iesalandalus.programacion.ficheros.secuencial.bytes.datosprimitivos;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class EscribeFicheroDatos {
	
public static final String FICHERO = "ficheros" + File.separator + "ficheroDatos.bin";
    
	public static void main(String[] args) {

		File fichero = new File(FICHERO);
		
		try (DataOutputStream salida = new DataOutputStream(new FileOutputStream(fichero))){
			escribirDatos(salida);
		} catch (IOException e) {
			System.out.println("No existe el fichero: " + FICHERO);
		}
	}

	private static void escribirDatos(DataOutputStream salida) {
		try {
			String datoString;
			int datoInt;
			double datoDouble;
			
			for (int i = 0; i < 10; i++) {
				datoString = "Cadena número: " + i;
				datoInt = 10*i;
				datoDouble = datoInt / 100.0;
				salida.writeUTF(datoString);
				salida.writeInt(datoInt);
				salida.writeDouble(datoDouble);
			}
			System.out.println("Fichero creado satisfactoriamente");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida");
		}
	}

}
