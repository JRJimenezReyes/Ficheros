package org.iesalandalus.programacion.ficheros.aleatorio.modelo.dao;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.iesalandalus.programacion.ficheros.aleatorio.modelo.dominio.Amigo;

public class Agenda {
	
	private static final String FICHERO_AMIGOS = String.format("%s%s%s", "ficheros", File.separator, "amigos.dat");

	private RandomAccessFile fichero;

	public void abrir() throws IOException {
		fichero = new RandomAccessFile(FICHERO_AMIGOS, "rw");
	}

	public void cerrar() throws IOException {
		if (fichero != null) {
			fichero.close();
		}
	}

	public RegistroAmigo leer() {
		RegistroAmigo registro = null;
		if (fichero != null) {
			try {
				registro = new RegistroAmigo();
				registro.leer(fichero);
			} catch (Exception error) {
				registro = null;
			}
		}
		return registro;
	}

	public RegistroAmigo leer(long indice) throws IOException {
		if (fichero != null) {
			fichero.seek((indice - 1) * RegistroAmigo.LONGITUD);
		}
		return leer();
	}
	
	public void escribir(Amigo amigo) throws IOException {
		RegistroAmigo registro = new RegistroAmigo(amigo);
		if (fichero != null) {
			registro.escribir(fichero);
		}
	}

	public void escribir(Amigo amigo, long indice) throws IOException {
		if (fichero != null) {
			fichero.seek((indice - 1) * RegistroAmigo.LONGITUD);
			escribir(amigo);
		}
	}
	
	public long getNumRegistros() throws IOException {
		return fichero.length() / RegistroAmigo.LONGITUD;
	}
	
	protected void setNumRegistros(long numRegistros) throws IOException {
		fichero.setLength(numRegistros * RegistroAmigo.LONGITUD);
	}
	
	public void listar() throws IOException {
		fichero.seek(0);
		for (long i = 1; i <= getNumRegistros(); i++) {
			System.out.println(leer(i));
		}
	}

	public void limpiar() throws IOException {
		fichero.setLength(0);
	}
	
	public void borrar(Amigo amigo) throws IOException {
		long indice = buscar(amigo);
		if (indice != -1) {
			desplazarIzquierda(indice);
		}
	}
	
	public long buscar(Amigo amigo) throws IOException {
		boolean encontrado = false;
		RegistroAmigo registro = null;
		long indiceRegistro = 1;
		while (indiceRegistro <= getNumRegistros() && !encontrado) {
			registro = leer(indiceRegistro++);
			encontrado = amigo.compareTo(registro) == 0;
		}
		return (encontrado) ? indiceRegistro - 1 : -1;
	}
	
	protected void desplazarIzquierda(long posicion) throws IOException {
		long numRegistros = getNumRegistros();	
		Amigo registroAuxiliar = null;
		for (long i = posicion; i < numRegistros; i++) {
			registroAuxiliar = leer(i + 1);
			escribir(registroAuxiliar, i);
		}
		setNumRegistros(numRegistros - 1);
	}

}
