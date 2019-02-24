package org.iesalandalus.programacion.ficheros.aleatorio.modelo.dao;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

import org.iesalandalus.programacion.ficheros.aleatorio.modelo.dominio.Amigo;

public class RegistroAmigo extends Amigo {
	private static final int LONGITUD_NOMBRE = 75; 			// 150 bytes =  75 caracteres * 2 byte/caracter UNICODE
	private static final int LONGITUD_TELEFONO = 9;			//  18 bytes =   9 caracteres * 2 byte/caracter UNICODE
	private static final int LONGITUD_CORREO = 50;			// 100 bytes =  50 caracteres * 2 byte/caracter UNICODE
	private static final int LONGITUD_DIRECCION = 100;		// 200 bytes = 100 caracteres * 2 byte/caracter UNICODE
	private static final int LONGITUD_F_NACIMIENTO = 10;	//  20 bytes =  10 caracteres * 2 byte/caracter UNICODE
	private static final int LONGITUD_PESO = 8;				//   8 bytes ocupa un double
	private static final int LONGITUD_ALTURA = 4;			//   4 bytes ocupa un integer
	public static final int LONGITUD = LONGITUD_NOMBRE  * 2 + LONGITUD_TELEFONO * 2 + LONGITUD_CORREO * 2 +
			LONGITUD_DIRECCION * 2 + LONGITUD_F_NACIMIENTO * 2 + LONGITUD_PESO + LONGITUD_ALTURA;
	
	public RegistroAmigo() {
		super();
	}
	
	public RegistroAmigo(Amigo amigo) {
		super(amigo);
	}

	public void leer(RandomAccessFile fichero) throws IOException {
		leerNombre(fichero);
		leerTelefono(fichero);
		leerCorreo(fichero);
		leerDireccion(fichero);
		leerFechaNacimiento(fichero);
		leerPeso(fichero);
		leerAltura(fichero);
	}

	public void escribir(RandomAccessFile fichero) throws IOException {
		escribirNombre(fichero);
		escribirTelefono(fichero);
		escribirCorreo(fichero);
		escribirDireccion(fichero);
		escribirFechaNacimiento(fichero);
		escribirPeso(fichero);
		escribirAltura(fichero);
	}
	
	private void leerNombre(RandomAccessFile fichero) throws IOException {
		setNombre(leerCadena(fichero, LONGITUD_NOMBRE));
	}
	
	private void escribirNombre(RandomAccessFile fichero) throws IOException {
		escribirCadena(fichero, getNombre(), LONGITUD_NOMBRE);
	}
	
	private void leerTelefono(RandomAccessFile fichero) throws IOException {
		setTelefono(leerCadena(fichero, LONGITUD_TELEFONO));
	}
	
	private void escribirTelefono(RandomAccessFile fichero) throws IOException {
		escribirCadena(fichero, getTelefono(), LONGITUD_TELEFONO);
	}

	private void leerCorreo(RandomAccessFile fichero) throws IOException {
		setCorreo(leerCadena(fichero, LONGITUD_CORREO));
	}
	
	private void escribirCorreo(RandomAccessFile fichero) throws IOException {
		escribirCadena(fichero, getCorreo(), LONGITUD_CORREO);
	}
	
	private void leerDireccion(RandomAccessFile fichero) throws IOException {
		setDireccion(leerCadena(fichero, LONGITUD_DIRECCION));
	}
	
	private void escribirDireccion(RandomAccessFile fichero) throws IOException {
		escribirCadena(fichero, getDireccion(), LONGITUD_DIRECCION);
	}
	
	private void leerFechaNacimiento(RandomAccessFile fichero) throws IOException {
		setFechaNacimiento(leerCadena(fichero, LONGITUD_F_NACIMIENTO));
	}
	
	private void escribirFechaNacimiento(RandomAccessFile fichero) throws IOException {
		escribirCadena(fichero, getFechaNacimientoStr(), LONGITUD_F_NACIMIENTO);
	}
	
	private void leerPeso(RandomAccessFile fichero) throws IOException {
		setPeso(fichero.readDouble());
	}
	
	private void escribirPeso(RandomAccessFile fichero) throws IOException {
		fichero.writeDouble(getPeso());
	}
	
	private void leerAltura(RandomAccessFile fichero) throws IOException {
		setAltura(fichero.readInt());
	}
	
	private void escribirAltura(RandomAccessFile fichero) throws IOException {
		fichero.writeInt(getAltura());
	}
	
	private String leerCadena(RandomAccessFile fichero, int tamano) throws IOException {
		char[] campo = new char[tamano];
		for (int i = 0; i < tamano; i++)
			campo[i] = fichero.readChar();
		return new String(campo).trim();
	}

	private void escribirCadena(RandomAccessFile fichero, String cadena, int tamano) throws IOException {
		char[] arrayCadena = Arrays.copyOf(cadena.toCharArray(), tamano);
		fichero.writeChars(new String(arrayCadena));
	}

}
