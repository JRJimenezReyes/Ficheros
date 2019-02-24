package org.iesalandalus.programacion.ficheros.aleatorio.modelo.dao;

import java.io.IOException;
import java.io.RandomAccessFile;

import org.iesalandalus.programacion.ficheros.aleatorio.modelo.dominio.Amigo;

public class Agenda {

	private RandomAccessFile fichero;

	public void abrir() throws IOException {
		fichero = new RandomAccessFile("ficheros/amigos.dat", "rw");
	}

	public void cerrar() throws IOException {
		if (fichero != null)
			fichero.close();
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

	public RegistroAmigo leer(long pos) throws IOException {
		if (fichero != null) {
			fichero.seek((pos - 1) * RegistroAmigo.LONGITUD);
		}
		return leer();
	}
	
	public void escribir(Amigo amigo) throws IOException {
		RegistroAmigo registro = new RegistroAmigo(amigo);
		if (fichero != null)
			registro.escribir(fichero);
	}

	public void escribir(Amigo amigo, long pos) throws IOException {
		if (fichero != null) {
			fichero.seek((pos - 1) * RegistroAmigo.LONGITUD);
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
		long pos = buscar(amigo);
		if (pos != -1) {
			desplazarIzquierda(pos);
		}
	}
	
	public long buscar(Amigo amigo) throws IOException {
		boolean encontrado = false;
		RegistroAmigo registro = null;
		long i = 1;
		while (i <= getNumRegistros() && !encontrado) {
			registro = leer(i++);
			encontrado = amigo.compareTo(registro) == 0;
		}
		return (encontrado) ? i-1 : -1;
	}
	
	protected void desplazarIzquierda(long pos) throws IOException {
		long numRegistros = getNumRegistros();	
		Amigo aux = null;
		for (long i = pos; i < numRegistros; i++) {
			aux = leer(i + 1);
			escribir(aux, i);
		}
		setNumRegistros(numRegistros - 1);
	}

}
