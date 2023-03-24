package org.iesalandalus.programacion.ficheros.aleatorio.modelo.dao;

import java.io.IOException;

import org.iesalandalus.programacion.ficheros.aleatorio.modelo.dominio.Amigo;

public class AgendaOrdenada extends Agenda {
	
	public AgendaOrdenada() {
		super();
	}
	
	public void insertar(Amigo amigo) throws IOException {
		long indice = buscar(amigo);
		if (indice != -1) {
			desplazarDerecha(indice);
			escribir(amigo, indice);
		} else {
			escribir(amigo);
		}
	}
	
	@Override
	public long buscar(Amigo amigo) throws IOException {
		return busquedaBinaria(amigo, 1, getNumRegistros());
	}

	@Override
	public void borrar(Amigo amigo) throws IOException {
		long indice = busquedaBinaria(amigo, 1, getNumRegistros());
		if (indice != -1) {
			desplazarIzquierda(indice);
		}
	}

	private long busquedaBinaria(Amigo amigo, long izquierda, long derecha) throws IOException {
		long indice = -1;
		long mitad = (izquierda + derecha) / 2;
		if (izquierda <= derecha) {
			Amigo amigoMitad = leer(mitad);
			if (amigo.compareTo(amigoMitad) < 0)
				indice = busquedaBinaria(amigo, izquierda, mitad - 1);
			else {
				if (amigo.compareTo(amigoMitad) == 0)
					indice = mitad;
				else
					indice = busquedaBinaria(amigo, mitad + 1, derecha);
			}
		} else {
			indice = mitad + 1;
		}
		return indice;
	}

	private void desplazarDerecha(long indice) throws IOException {
		long numRegistros = getNumRegistros();
		setNumRegistros(numRegistros + 1);
		Amigo amigoAuxiliar = null;
		for (long i = numRegistros; i > indice - 1; i--) {
			amigoAuxiliar = leer(i);
			escribir(amigoAuxiliar, i + 1);
		}
	}

}
