package org.iesalandalus.programacion.xml;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;

import org.iesalandalus.programacion.ficheros.secuencial.bytes.objetos.Persona;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ConvertirFicheroObjetosAXmlConElementos {
	
	private static final String FICHERO_OBJETOS = "ficheros" + File.separator + "personas.dat";
	private static final String FICHERO_XML = "ficheros" + File.separator + "personasElementos.xml";
	
	public static void main(String[] args) {
		List<Persona> personas = leerFicheroObjetos(new File(FICHERO_OBJETOS));
		Document documento = crearDom(personas);
		UtilidadesXml.escribirXmlAFichero(documento, new File(FICHERO_XML));
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
	
	private static Document crearDom(List<Persona> personas) {
		DocumentBuilder constructor = UtilidadesXml.crearConstructorDocumentoXml();
		Document documentoXml = null;
		if (constructor != null) {
			documentoXml = constructor.newDocument();
			documentoXml.appendChild(documentoXml.createElement("personas"));
			for (Persona persona : personas) {
				Element elementoPersona = crearElementoPersonaConElementos(documentoXml, persona);
				documentoXml.getDocumentElement().appendChild(elementoPersona);
			}
		}
		return documentoXml;
	}

	private static Element crearElementoPersonaConElementos(Document documentoXml, Persona persona) {
		Element elementoPersona = documentoXml.createElement("persona");
		Element elementoNombre = documentoXml.createElement("nombre");
		elementoNombre.setTextContent(persona.getNombre());
		Element elementoEdad = documentoXml.createElement("edad");
		elementoEdad.setTextContent(String.format("%d", persona.getEdad()));
		elementoPersona.appendChild(elementoNombre);
		elementoPersona.appendChild(elementoEdad);
		return elementoPersona;
	}

}
