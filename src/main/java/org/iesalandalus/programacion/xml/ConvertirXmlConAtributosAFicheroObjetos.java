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

public class ConvertirXmlConAtributosAFicheroObjetos {
	
	private static final String FICHERO_OBJETOS = "ficheros" + File.separator + "personas.dat";
	private static final String FICHERO_XML = "ficheros" + File.separator + "personasAtributos.xml";	
	
	public static void main(String[] args) {
		Document documento = UtilidadesXml.leerXmlDeFichero(new File(FICHERO_XML));
		if (documento != null) {
			System.out.println("Fichero XML leído correctamente.");
			escribirXmlConAtributosAFicheroObjetos(documento, new File(FICHERO_OBJETOS));
		} else {
			System.out.println("No se ha podido leer el fichero XML.");
		}
	}
	
	private static void escribirXmlConAtributosAFicheroObjetos(Document documento, File fichero) {
		try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(fichero))){
			NodeList personas = documento.getElementsByTagName("persona");
			for (int i = 0; i < personas.getLength(); i++) {
				Node nPersona = personas.item(i);
				if (nPersona.getNodeType() == Node.ELEMENT_NODE) {
					String nombre = ((Element)nPersona).getAttribute("nombre");
					int edad = Integer.parseInt(((Element)nPersona).getAttribute("edad"));
					salida.writeObject(new Persona(nombre, edad));
				}
			}
			System.out.println("Fichero de objetos escrito correctamente.");
		} catch (IOException e) {
			System.out.println("No puedo abrir el fihero de salida.");
		}
	}
}
