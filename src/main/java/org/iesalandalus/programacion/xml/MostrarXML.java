package org.iesalandalus.programacion.xml;

import java.io.File;
import java.io.StringWriter;

import javax.xml.XMLConstants;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class MostrarXML {
	
	private static final String FICHERO_XML = String.format("%s%s%s", "ficheros", File.separator, "ejemplo.xml");
	
	public static void main(String[] args) {
		Document documentoXml = UtilidadesXml.leerDocumentoXml(FICHERO_XML);
		if (documentoXml != null) {
			System.out.println("Fichero XML leído correctamente.");
			mostrarDocumentoXml(documentoXml);
		}
	}
	
	private static void mostrarDocumentoXml(Document documentoXml) {
        Transformer conversor = null;
        StreamResult destino = null;
		try {
			TransformerFactory factoria = TransformerFactory.newInstance();
			factoria.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
			factoria.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
			conversor = factoria.newTransformer();            
	        StringWriter documentoXMLTexto = new StringWriter();
			destino = new StreamResult(documentoXMLTexto);            
	        DOMSource fuente = new DOMSource(documentoXml);
	        conversor.transform(fuente, destino);    
	        System.out.println(documentoXMLTexto.toString());
		} catch (TransformerConfigurationException | TransformerFactoryConfigurationError e) {
			System.out.println("Imposible crear el conversor.");
		} catch (TransformerException e) {
			System.out.println("Error irrecuperable en la conversión.");
		}
	}
}
