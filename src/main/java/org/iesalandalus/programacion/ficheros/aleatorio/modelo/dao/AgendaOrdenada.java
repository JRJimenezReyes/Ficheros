package org.iesalandalus.programacion.ficheros.aleatorio.modelo.dao;

import java.io.IOException;

import org.iesalandalus.programacion.ficheros.aleatorio.modelo.dominio.Amigo;

public class AgendaOrdenada extends Agenda {
	
	public AgendaOrdenada() {
		super();
	}
	
	public void insertar(Amigo amigo) throws IOException {
		long pos = buscar(amigo);
		if (pos != -1) {
			desplazarDerecha(pos);
			escribir(amigo, pos);
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
		long pos = busquedaBinaria(amigo, 1, getNumRegistros());
		if (pos != -1) {
			desplazarIzquierda(pos);
		}
	}

	private long busquedaBinaria(Amigo amigo, long izq, long der) throws IOException {
		long pos = -1;
		long mitad = (izq + der) / 2;
		if (izq <= der) {
			Amigo amigoMitad = leer(mitad);
			if (amigo.compareTo(amigoMitad) < 0)
				pos = busquedaBinaria(amigo, izq, mitad - 1);
			else {
				if (amigo.compareTo(amigoMitad) == 0)
					pos = mitad;
				else
					pos = busquedaBinaria(amigo, mitad + 1, der);
			}
		} else {
			pos = mitad + 1;
		}
		return pos;
	}

	private void desplazarDerecha(long pos) throws IOException {
		long numRegistros = getNumRegistros();
		setNumRegistros(numRegistros + 1);
		Amigo aux = null;
		for (long i = numRegistros; i > pos - 1; i--) {
			aux = leer(i);
			escribir(aux, i + 1);
		}
	}

}
