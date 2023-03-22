package org.iesalandalus.programacion.xml;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ConvertirXmlConElementosAFicheroDatosPrimitivos {
	
	private static final String FICHERO_DATOS_PRIMITIVOS = "ficheros" + File.separator + "ficheroDatos.bin";
	private static final String FICHERO_XML = "ficheros" + File.separator + "datosElementos.xml";	
	
	public static void main(String[] args) {
		Document documento = UtilidadesXml.leerXmlDeFichero(new File(FICHERO_XML));
		if (documento != null) {
			System.out.println("Fichero XML leído correctamente.");
			escribirXmlConElementosAFicheroDatosPrimitivos(documento, new File(FICHERO_DATOS_PRIMITIVOS));
		} else {
			System.out.println("No se ha podido leer el fichero XML.");
		}
	}
	
	private static void escribirXmlConElementosAFicheroDatosPrimitivos(Document documento, File fichero) {
		try (DataOutputStream salida = new DataOutputStream(new FileOutputStream(fichero))){
			NodeList personas = documento.getElementsByTagName("dato");
			for (int i = 0; i < personas.getLength(); i++) {
				Node nPersona = personas.item(i);
				if (nPersona.getNodeType() == Node.ELEMENT_NODE) {
					salida.writeUTF(((Element)nPersona).getElementsByTagName("cadena").item(0).getTextContent());
					salida.writeInt(Integer.parseInt(((Element)nPersona).getElementsByTagName("entero").item(0).getTextContent()));
					salida.writeDouble(Double.parseDouble(((Element)nPersona).getElementsByTagName("doble").item(0).getTextContent().replace(',', '.')));
				}
			}
			System.out.println("Fichero de objetos escrito correctamente.");
		} catch (IOException e) {
			System.out.println("No puedo abrir el fihero de salida.");
		}
	}
}
