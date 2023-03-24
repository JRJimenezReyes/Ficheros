package org.iesalandalus.programacion.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.iesalandalus.programacion.ficheros.secuencial.bytes.objetos.Persona;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ConvertirXmlConElementosAFicheroObjetos {
	
	private static final String FICHERO_OBJETOS = String.format("%s%s%s", "ficheros", File.separator, "personas.dat");
	private static final String FICHERO_XML = String.format("%s%s%s", "ficheros", File.separator, "personasElementos.xml");	
	
	public static void main(String[] args) {
		Document documento = UtilidadesXml.leerXmlDeFichero(FICHERO_XML);
		if (documento != null) {
			System.out.println("Fichero XML leído correctamente.");
			escribirXmlConElementosAFicheroObjetos(documento);
		} else {
			System.out.printf("No se puede leer el fichero de entrada: %s.%n", FICHERO_XML);
		}
	}
	
	private static void escribirXmlConElementosAFicheroObjetos(Document documento) {
		try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(FICHERO_OBJETOS))){
			NodeList personas = documento.getElementsByTagName("persona");
			for (int i = 0; i < personas.getLength(); i++) {
				Node persona = personas.item(i);
				if (persona.getNodeType() == Node.ELEMENT_NODE) {
					String nombre = ((Element)persona).getElementsByTagName("nombre").item(0).getTextContent();
					int edad = Integer.parseInt(((Element)persona).getElementsByTagName("edad").item(0).getTextContent());
					salida.writeObject(new Persona(nombre, edad));
				}
			}
			System.out.println("Fichero de objetos escrito correctamente.");
		} catch (IOException e) {
			System.out.printf("No existe el directorio de destino o no tengo permiso de escritura: %s.%n", FICHERO_OBJETOS);
		}
	}
}
