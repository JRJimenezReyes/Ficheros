package org.iesalandalus.programacion.xml;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ConvertirFicheroDatosPrimitivosAXmlConAtributos {

	private static final String FICHERO_DATOS_PRIMITIVOS = String.format("%s%s%s", "ficheros", File.separator, "ficheroDatos.bin");
	private static final String FICHERO_XML = String.format("%s%s%s", "ficheros", File.separator, "datosAtributos.xml");

	public static void main(String[] args) {
		List<DatoPrimitivo> personas = leerFicheroDatosPrimitvos();
		Document documentoXml = crearDocumentoXml(personas);
		UtilidadesXml.escribirDocumentoXml(documentoXml, FICHERO_XML);
	}

	private static List<DatoPrimitivo> leerFicheroDatosPrimitvos() {
		List<DatoPrimitivo> datosPrimitvos = new ArrayList<>();
		try (DataInputStream entrada = new DataInputStream(new FileInputStream(FICHERO_DATOS_PRIMITIVOS))){
			String cadena = "";
			int entero;
			double doble;
			while (cadena != null) { //Esta condición siempre será verdadera
				cadena = entrada.readUTF();
				entero = entrada.readInt();
				doble = entrada.readDouble();
				datosPrimitvos.add(new DatoPrimitivo(cadena, entero, doble));
			}
		} catch (FileNotFoundException fnfe) {
			System.out.printf("No se puede leer el fichero de entrada: %s.%n", FICHERO_DATOS_PRIMITIVOS);
		}  catch (EOFException eo) {
			System.out.println("Fichero de datos primitivos leído satisfactoriamente.");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida.");
		}
		return datosPrimitvos;
	}

	private static Document crearDocumentoXml(List<DatoPrimitivo> datosPrimitvos) {
		DocumentBuilder constructor = UtilidadesXml.crearConstructorDocumentoXml();
		Document documentoXml = null;
		if (constructor != null) {
			documentoXml = constructor.newDocument();
			documentoXml.appendChild(documentoXml.createElement("datosPrimitivos"));
			for (DatoPrimitivo datoPrimitivo : datosPrimitvos) {
				Element elementoDato = crearElementoConAtributos(documentoXml, datoPrimitivo);
				documentoXml.getDocumentElement().appendChild(elementoDato);
			}
		}
		return documentoXml;
	}

	private static Element crearElementoConAtributos(Document documentoXml, DatoPrimitivo datoPrimitivo) {
		Element elementoPersona = documentoXml.createElement("dato");
		elementoPersona.setAttribute("cadena", datoPrimitivo.cadena);
		elementoPersona.setAttribute("entero", String.format("%d", datoPrimitivo.entero));
		elementoPersona.setAttribute("doble", String.format("%f", datoPrimitivo.doble));
		return elementoPersona;
	}

}
