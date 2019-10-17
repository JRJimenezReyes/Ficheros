package org.iesalandalus.programacion.xml;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ConvertirXMLConElementosAFicheroDatosPrimitivos {
	
	private static final String FICHERO_DATOS_PRIMITIVOS = "ficheros" + File.separator + "ficheroDatos.bin";
	private static final String FICHERO_XML = "ficheros" + File.separator + "datosAtributos.xml";	
	
	public static void main(String[] args) {
		Document documento = leerXML(new File(FICHERO_XML));
		if (documento != null) {
			escribirXMLConAtributosAFichero(documento, new File(FICHERO_DATOS_PRIMITIVOS));
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
		try (DataOutputStream salida = new DataOutputStream(new FileOutputStream(fichero))){
			NodeList personas = documento.getElementsByTagName("dato");
			for (int i = 0; i < personas.getLength(); i++) {
				Node nPersona = personas.item(i);
				if (nPersona.getNodeType() == Node.ELEMENT_NODE) {
					salida.writeUTF(((Element)nPersona).getAttribute("cadena"));
					salida.writeInt(Integer.parseInt(((Element)nPersona).getAttribute("entero")));
					salida.writeDouble(Double.parseDouble(((Element)nPersona).getAttribute("doble").replace(',', '.')));
				}
			}
			System.out.println("Fichero de objetos escrito correctamente.");
		} catch (IOException e) {
			System.out.println("No puedo abrir el fihero de salida.");
		}
	}
}
