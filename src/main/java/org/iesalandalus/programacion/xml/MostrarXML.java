package org.iesalandalus.programacion.xml;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class MostrarXML {
	
	private static final String FICHERO_XML = "ficheros" + File.separator + "ejemplo.xml";
	
	public static void main(String[] args) {
		Document documento = leerXML(new File(FICHERO_XML));
		mostrarDocumentoXML(documento);
	}

	private static Document leerXML(File ficheroXML) {
		Document documentoXML = null;
		try {
			DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
			factoria.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			DocumentBuilder constructor = factoria.newDocumentBuilder();
			documentoXML = constructor.parse(ficheroXML);
		} catch (ParserConfigurationException e) {
			System.out.println("Error al crear el constructor.");
		} catch (SAXException e) {
			System.out.println("Documento XML mal formado.");
		} catch (IOException e) {
			System.out.println("Error inesperado de E/S.");
		}
		return documentoXML;
	}
	
	private static void mostrarDocumentoXML(Document documento) {
        Transformer conversor = null;
        StreamResult destino = null;
		try {
			TransformerFactory factoria = TransformerFactory.newInstance();
			factoria.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			conversor = factoria.newTransformer();            
	        StringWriter documentoXMLTexto = new StringWriter();
			destino = new StreamResult(documentoXMLTexto);            
	        DOMSource fuente = new DOMSource(documento);
	        conversor.transform(fuente, destino);    
	        System.out.println(documentoXMLTexto.toString());
		} catch (TransformerConfigurationException | TransformerFactoryConfigurationError e) {
			System.out.println("Imposible crear el conversor.");
		} catch (TransformerException e) {
			System.out.println("Error irecuperable en la conversión.");
		}
	}
}
