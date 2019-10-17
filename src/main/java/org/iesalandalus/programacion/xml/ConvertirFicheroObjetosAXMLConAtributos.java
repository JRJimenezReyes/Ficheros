package org.iesalandalus.programacion.xml;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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

import org.iesalandalus.programacion.ficheros.secuencial.bytes.objetos.Persona;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ConvertirFicheroObjetosAXMLConAtributos {
	
	private static final String FICHERO_OBJETOS = "ficheros" + File.separator + "personas.dat";
	private static final String FICHERO_XML = "ficheros" + File.separator + "personasAtributos.xml";
	
	public static void main(String[] args) {
		List<Persona> personas = leerFicheroObjetos(new File(FICHERO_OBJETOS));
		
		Document documento = crearDOM(personas);
		
		escribirXMLAFichero(documento, new File(FICHERO_XML));
	}
	
	private static List<Persona> leerFicheroObjetos(File fichero) {
		List<Persona> personas = new ArrayList<>();
		try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(fichero))){
			Persona persona;
			while ((persona = (Persona)entrada.readObject()) != null) {
				personas.add(persona);
			}
		} catch (EOFException e) {
			System.out.println("Fichero leído satisfactoriamente.");
		} catch (ClassNotFoundException e) {
			System.out.println("No puedo encontrar la clase que tengo que leer.");
		} catch (IOException e) {
			System.out.println("No puedo abrir el fihero de entrada.");
		}
		return personas;
	}
	

	private static Document crearDOM(List<Persona> personas) {
		DocumentBuilder constructor = null;
		try {
			DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
			factoria.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			constructor = factoria.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			System.out.println("Error al crear el constructor.");
		}
		Document documentoXML = null;
		if (constructor != null) {
			documentoXML = constructor.newDocument();
			documentoXML.appendChild(documentoXML.createElement("personas"));
			for (Persona persona : personas) {
				Element elementoPersona = crearElementoPersonaConAtributos(documentoXML, persona);
				documentoXML.getDocumentElement().appendChild(elementoPersona);
			}
		}
		return documentoXML;
	}

	private static Element crearElementoPersonaConAtributos(Document documentoXML, Persona persona) {
		Element elementoPersona = documentoXML.createElement("persona");
		elementoPersona.setAttribute("nombre", persona.getNombre());
		elementoPersona.setAttribute("edad", String.format("%d", persona.getEdad()));
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
