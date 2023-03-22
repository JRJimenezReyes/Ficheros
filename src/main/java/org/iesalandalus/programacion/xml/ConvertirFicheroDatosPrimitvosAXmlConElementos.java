package org.iesalandalus.programacion.xml;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ConvertirFicheroDatosPrimitvosAXmlConElementos {
	
	private static final String FICHERO_DATOS_PRIMITIVOS = "ficheros" + File.separator + "ficheroDatos.bin";
	private static final String FICHERO_XML = "ficheros" + File.separator + "datosElementos.xml";
	
	public static void main(String[] args) {
		List<DatoPrimitivo> personas = leerFicheroDatosPrimitvos(new File(FICHERO_DATOS_PRIMITIVOS));
		Document documento = crearDom(personas);
		UtilidadesXml.escribirXmlAFichero(documento, new File(FICHERO_XML));
	}
	
	private static List<DatoPrimitivo> leerFicheroDatosPrimitvos(File fichero) {
		List<DatoPrimitivo> datosPrimitvos = new ArrayList<>();
		try (DataInputStream entrada = new DataInputStream(new FileInputStream(fichero))){
			leerDatosPrimitivos(datosPrimitvos, entrada);
		} catch (IOException e) {
			System.out.println("No puedo abrir el fihero de entrada.");
		}
		return datosPrimitvos;
	}

	private static void leerDatosPrimitivos(List<DatoPrimitivo> datosPrimitvos, DataInputStream entrada) {
		try {
			String cadena = "";
			int entero;
			double doble;
			while (cadena != null) { //Esta condición siempre será verdadera
				cadena = entrada.readUTF();
				entero = entrada.readInt();
				doble = entrada.readDouble();
				datosPrimitvos.add(new DatoPrimitivo(cadena, entero, doble));
			}
		} catch (EOFException e) {
			System.out.println("Fichero leído satisfactoriamente.");
		} catch (IOException e) {
			System.out.println("Error inesperado de E/S.");
		}
	}
	
	private static Document crearDom(List<DatoPrimitivo> datosPrimitvos) {
		DocumentBuilder constructor = UtilidadesXml.crearConstructorDocumentoXml();
		Document documentoXml = null;
		if (constructor != null) {
			documentoXml = constructor.newDocument();
			documentoXml.appendChild(documentoXml.createElement("datosPrimitvos"));
			for (DatoPrimitivo datoPrimitivo : datosPrimitvos) {
				Element elementoDato = crearElementoConElementos(documentoXml, datoPrimitivo);
				documentoXml.getDocumentElement().appendChild(elementoDato);
			}
		}
		return documentoXml;
	}

	private static Element crearElementoConElementos(Document documentoXml, DatoPrimitivo datoPrimitivo) {
		Element elementoDato = documentoXml.createElement("dato");
		Element elementoCadena = documentoXml.createElement("cadena");
		elementoCadena.setTextContent(datoPrimitivo.cadena);
		Element elementoEntero = documentoXml.createElement("entero");
		elementoEntero.setTextContent(String.format("%d", datoPrimitivo.entero));
		Element elementoDoble = documentoXml.createElement("doble");
		elementoDoble.setTextContent(String.format("%f", datoPrimitivo.doble));
		elementoDato.appendChild(elementoCadena);
		elementoDato.appendChild(elementoEntero);
		elementoDato.appendChild(elementoDoble);
		return elementoDato;
	}
	
}
