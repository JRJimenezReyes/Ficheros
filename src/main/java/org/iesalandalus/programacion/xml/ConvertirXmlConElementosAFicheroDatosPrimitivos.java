package org.iesalandalus.programacion.xml;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ConvertirXmlConElementosAFicheroDatosPrimitivos {
	
	private static final String FICHERO_DATOS_PRIMITIVOS = String.format("%s%s%s", "ficheros", File.separator, "ficheroDatos.bin");
	private static final String FICHERO_XML = String.format("%s%s%s", "ficheros", File.separator, "datosElementos.xml");
	
	public static void main(String[] args) {
		Document documentoXml = UtilidadesXml.leerDocumentoXml(FICHERO_XML);
		if (documentoXml != null) {
			System.out.println("Fichero XML leído correctamente.");
			escribirXmlConElementosAFicheroDatosPrimitivos(documentoXml);
		}
	}
	
	private static void escribirXmlConElementosAFicheroDatosPrimitivos(Document documentoXml) {
		try (DataOutputStream salida = new DataOutputStream(new FileOutputStream(FICHERO_DATOS_PRIMITIVOS))){
			NodeList personas = documentoXml.getElementsByTagName("dato");
			for (int i = 0; i < personas.getLength(); i++) {
				Node persona = personas.item(i);
				if (persona.getNodeType() == Node.ELEMENT_NODE) {
					salida.writeUTF(((Element)persona).getElementsByTagName("cadena").item(0).getTextContent());
					salida.writeInt(Integer.parseInt(((Element)persona).getElementsByTagName("entero").item(0).getTextContent()));
					salida.writeDouble(Double.parseDouble(((Element)persona).getElementsByTagName("doble").item(0).getTextContent().replace(',', '.')));
				}
			}
			System.out.println("Fichero de objetos escrito correctamente.");
		}  catch (FileNotFoundException e) {
			System.out.printf("No se ha podido escribir el fichero %s.%n", FICHERO_DATOS_PRIMITIVOS);
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida.");
		}
	}
}
