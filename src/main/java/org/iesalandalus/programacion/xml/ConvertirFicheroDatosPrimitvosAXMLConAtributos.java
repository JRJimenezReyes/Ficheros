package org.iesalandalus.programacion.xml;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

class Dato {
	
	protected String cadena;
	protected int entero;
	protected double doble;
	
	protected Dato(String cadena, int entero, double doble) {
		this.cadena = cadena;
		this.entero = entero;
		this.doble = doble;
	}
}

public class ConvertirFicheroDatosPrimitvosAXMLConAtributos {
	
	private static final String FICHERO_DATOS_PRIMITIVOS = "ficheros" + File.separator + "ficheroDatos.bin";
	private static final String FICHERO_XML = "ficheros" + File.separator + "datosAtributos.xml";
	
	public static void main(String[] args) {
		List<Dato> personas = leerFicheroDatosPrimitvos(new File(FICHERO_DATOS_PRIMITIVOS));
		
		Document documento = crearDOM(personas);
		
		escribirXMLAFichero(documento, new File(FICHERO_XML));
	}
	
	private static List<Dato> leerFicheroDatosPrimitvos(File fichero) {
		List<Dato> datosPrimitvos = new ArrayList<>();
		try (DataInputStream entrada = new DataInputStream(new FileInputStream(fichero))){
			String cadena = "";
			int entero = 0;
			double doble = 0.0;
			try {
				while (true) {
					cadena = entrada.readUTF();
					entero = entrada.readInt();
					doble = entrada.readDouble();
					datosPrimitvos.add(new Dato(cadena, entero, doble));
				}
			} catch (EOFException eo) {
				System.out.println("Fichero leído satisfactoriamente.");
			} catch (IOException e) {
				System.out.println("Error inesperado de Entrada/Salida.");
			}
		} catch (IOException e) {
			System.out.println("No puedo abrir el fihero de entrada.");
		}
		return datosPrimitvos;
	}
	

	private static Document crearDOM(List<Dato> datosPrimitvos) {
		DocumentBuilder constructor = null;
		try {
			DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
			factoria.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			constructor = factoria.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			System.out.println("Error al crear el constructor.");
		}
		Document documentoXML = constructor.newDocument();
		documentoXML.appendChild(documentoXML.createElement("datosPrimitvos"));
		for (Dato dato : datosPrimitvos) {
			Element elementoDato = crearElementoDatoConAtributos(documentoXML, dato);
			documentoXML.getDocumentElement().appendChild(elementoDato);
		}
		return documentoXML;
	}

	private static Element crearElementoDatoConAtributos(Document documentoXML, Dato dato) {
		Element elementoPersona = documentoXML.createElement("dato");
		elementoPersona.setAttribute("cadena", dato.cadena);
		elementoPersona.setAttribute("entero", String.format("%d", dato.entero));
		elementoPersona.setAttribute("doble", String.format("%f", dato.doble));
		return elementoPersona;
	}
	
	private static void escribirXMLAFichero(Document documento, File salida) {

		try {
			TransformerFactory factoria = TransformerFactory.newInstance();
			factoria.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			Transformer conversor = factoria.newTransformer(); 
            conversor.setOutputProperty(OutputKeys.INDENT, "yes");
            conversor.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); 
            conversor.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","4");
            StreamResult destino = new StreamResult(salida);            
            DOMSource fuente = new DOMSource(documento);
            conversor.transform(fuente, destino);
            System.out.println("Fichero XML escrito satisfactoriamente.");
		} catch (TransformerConfigurationException | TransformerFactoryConfigurationError e) {
			System.out.println("Imposible crear el conversor.");
		} catch (TransformerException e) {
			System.out.println("Error irecuperable en la conversión.");
		}

	}
}
