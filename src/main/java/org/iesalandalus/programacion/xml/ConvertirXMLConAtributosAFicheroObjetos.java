package org.iesalandalus.programacion.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.iesalandalus.programacion.ficheros.secuencial.bytes.objetos.Persona;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ConvertirXMLConAtributosAFicheroObjetos {
	
	private static final String FICHERO_OBJETOS = "ficheros" + File.separator + "personas.dat";
	private static final String FICHERO_XML = "ficheros" + File.separator + "personasAtributos.xml";	
	
	public static void main(String[] args) {
		Document documento = leerXML(new File(FICHERO_XML));
		if (documento != null) {
			escribirXMLConAtributosAFichero(documento, new File(FICHERO_OBJETOS));
		}
	}

	private static Document leerXML(File ficheroXML) {
		Document documentoXML = null;
		try {
			DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
			factoria.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			DocumentBuilder constructor = factoria.newDocumentBuilder();
			documentoXML = constructor.parse(ficheroXML);
			System.out.println("Fichero XML leído correctamente.");
		} catch (ParserConfigurationException e) {
			System.out.println("Error al crear el constructor.");
		} catch (SAXException e) {
			System.out.println("Documento XML mal formado.");
		} catch (IOException e) {
			System.out.println("Error inesperado de E/S.");
		}
		return documentoXML;
	}
	
	private static void escribirXMLConAtributosAFichero(Document documento, File fichero) {
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
