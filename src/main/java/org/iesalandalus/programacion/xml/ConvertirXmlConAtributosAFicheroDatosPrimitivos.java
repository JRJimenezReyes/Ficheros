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

public class ConvertirXmlConAtributosAFicheroDatosPrimitivos {
	
	private static final String FICHERO_DATOS_PRIMITIVOS = String.format("%s%s%s", "ficheros", File.separator, "ficheroDatos.bin");
	private static final String FICHERO_XML = String.format("%s%s%s", "ficheros", File.separator, "datosAtributos.xml");	
	
	public static void main(String[] args) {
		Document documento = UtilidadesXml.leerXmlDeFichero(FICHERO_XML);
		if (documento != null) {
			System.out.println("Fichero XML leído correctamente.");
			escribirXmlConAtributosAFicheroDatosPrimitivos(documento);
		} else {
			System.out.printf("No se puede leer el fichero de entrada: %s.%n", FICHERO_XML);
		}
	}
	
	private static void escribirXmlConAtributosAFicheroDatosPrimitivos(Document documento) {
		try (DataOutputStream salida = new DataOutputStream(new FileOutputStream(FICHERO_DATOS_PRIMITIVOS))){
			NodeList personas = documento.getElementsByTagName("dato");
			for (int i = 0; i < personas.getLength(); i++) {
				Node persona = personas.item(i);
				if (persona.getNodeType() == Node.ELEMENT_NODE) {
					salida.writeUTF(((Element)persona).getAttribute("cadena"));
					salida.writeInt(Integer.parseInt(((Element)persona).getAttribute("entero")));
					salida.writeDouble(Double.parseDouble(((Element)persona).getAttribute("doble").replace(',', '.')));
				}
			}
			System.out.println("Fichero de objetos escrito correctamente.");
		} catch (FileNotFoundException e) {
			System.out.printf("No existe el directorio de destino o no tengo permiso de escritura: %s.%n", FICHERO_DATOS_PRIMITIVOS);
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida.");
		}
	}
}
