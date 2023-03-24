package org.iesalandalus.programacion.ficheros.aleatorio;

import java.io.IOException;

import org.iesalandalus.programacion.ficheros.aleatorio.modelo.dao.Agenda;
import org.iesalandalus.programacion.ficheros.aleatorio.modelo.dominio.Amigo;

public class MainApp {
	public static void main(String[] args) throws IOException {
		Amigo amigo1 = new Amigo("Paco Jones", "950456789", "paco.jones@iesalandalus.org", 
				"Parque", "01/02/2003", 75.4, 183);
		Amigo amigo2 = new Amigo("Juan Sin Miedo", "950122223", "juansinmiedo@iesalandalus.org",
				"Casa de los Horrores", "11/12/2013", 65.8, 150);
		Amigo amigo3 = new Amigo("Bob Esponja", "950233445", "bobesponja@iesalandalus.org",
				"Fondo de Bikini", "05/06/2007", 25.8, 100);
		
		Agenda agenda = new Agenda();
		agenda.abrir();
		agenda.escribir(amigo1);
		agenda.escribir(amigo2);
		agenda.escribir(amigo3);
		agenda.cerrar();
		agenda.abrir();
		agenda.listar();
		System.out.printf("Posición amigo1: %s%n", agenda.buscar(amigo1));
		agenda.borrar(amigo1);
		agenda.listar();
		System.out.printf("Posición amigo2: %s%n", agenda.buscar(amigo2));
		agenda.borrar(amigo2);
		agenda.listar();
		System.out.printf("Posición amigo3: %s%n", agenda.buscar(amigo3));
		agenda.borrar(amigo3);
		agenda.listar();

		agenda.cerrar();
	}

}
