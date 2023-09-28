/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.xmlprogram;

/**
 *
 * @author Fernando
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Clase que representa operaciones con archivos XML.
 */
class fileXML {

    /**
     * Abre un archivo XML y devuelve su contenido como un objeto Document.
     *
     * @param name El nombre del archivo XML que se va a abrir.
     * @return Un objeto Document que representa el contenido del archivo.
     * @throws IOException Si ocurre un error al abrir el archivo.
     * @throws SAXException Si ocurre un error de análisis SAX al procesar el archivo.
     * @throws ParserConfigurationException Si ocurre un error de configuración del analizador XML.
     * @throws FileNotFoundException Si el archivo XML especificado no se encuentra.
     */
    public Document OpenXML(String name) throws IOException, SAXException, ParserConfigurationException, FileNotFoundException {

        // Crea una instancia del analizador de documentos XML.
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document dom = null;

        try {
            // Parsea el archivo XML especificado.
            dom = dBuilder.parse(name);

        } catch (IOException e) {
            // Manejar la excepción en caso de error de lectura del archivo.
        }
        return dom;
    }

    /**
     * Imprime los módulos contenidos en el elemento raíz del documento XML.
     *
     * @param root El elemento raíz del documento XML.
     */
    public void printModules(Element root) {
        NodeList modules = root.getElementsByTagName("module");
        for (int i = 0; i < modules.getLength(); i++) {
            Element e = (Element) modules.item(i);

            // Imprime información sobre cada módulo.
            System.out.println("Nombre: " + e.getElementsByTagName("name").item(0).getFirstChild().getNodeValue());
            System.out.println("Horas: " + e.getElementsByTagName("hours").item(0).getFirstChild().getNodeValue());
            System.out.println("Tiempo: " + e.getElementsByTagName("time").item(0).getFirstChild().getNodeValue());
            System.out.println("Student: " + e.getElementsByTagName("student").item(0).getFirstChild().getNodeValue());
        }
    }

    /**
     * Escribe un nuevo módulo en un archivo XML.
     *
     * @param file El archivo XML en el que se va a escribir el módulo.
     * @throws SAXException Si ocurre un error de análisis SAX.
     * @throws ParserConfigurationException Si ocurre un error de configuración del analizador XML.
     * @throws TransformerConfigurationException Si ocurre un error de configuración del transformador XML.
     * @throws FileNotFoundException Si el archivo XML especificado no se encuentra.
     * @throws TransformerException Si ocurre un error durante la transformación XML.
     */
    public void writeModules(File file) throws SAXException, ParserConfigurationException,
            TransformerConfigurationException, FileNotFoundException, TransformerException {

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

        // Crear un nuevo documento XML.
        Document doc = dBuilder.newDocument();
        try {
            // Crear elementos
            Element rootElement = doc.createElement("course");
            doc.appendChild(rootElement);

            Element new_module = crearModulo(doc);

            rootElement.appendChild(new_module);
        } catch (Exception e) {
            System.out.println(e);
        }

        // Realizar la transformación y escribir el documento en el archivo especificado.
        Transformer trans = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new FileOutputStream(file));
        trans.transform(source, result);
    }

    /**
     * Crea un nuevo modulo
     *
     * @param doc El documento XML
     * @return Un nuevo modulo
     */
    public static Element crearModulo(Document doc) {

        // Crea nuevos elementos
        Element module = doc.createElement("module");
        Element name = doc.createElement("name");
        Element hours = doc.createElement("hours");
        Element currentTime = doc.createElement("time");
        Element student = doc.createElement("student");

        // Configurar valores para el nuevo módulo.
        name.appendChild(doc.createTextNode("Acceso a datos"));
        hours.appendChild(doc.createTextNode(Integer.toString(8)));
        student.appendChild(doc.createTextNode("Fernando"));

        LocalDateTime now = LocalDateTime.now();
        currentTime.appendChild(doc.createTextNode(now.toString()));

        // Agregar los elementos.
        module.appendChild(name);
        module.appendChild(hours);
        module.appendChild(currentTime);
        module.appendChild(student);

        return module;
    }

    /**
     *
     * Busca un módulo por su nombre en un documento XML y muestra su información si se encuentra.
     *
     * @param moduleName El nombre del módulo que se va a buscar.
     * @param doc El objeto Document que representa el contenido del archivo XML.
     */
    public void findModule(String moduleName, Document doc) {

        // Variable para saber si lo hemos encontrado
        boolean encontrado = false;
        // Obtiene una lista de todos los elementos "module"
        NodeList modules = doc.getElementsByTagName("module");

        // Itera a través de la lista de módulos.
        for (int i = 0; i < modules.getLength(); i++) {

            // Obtenemos el módulo actual
            Element module = (Element) modules.item(i);

            // Obtiene el nombre del modulo actual
            String name = module.getElementsByTagName("name").item(0).getTextContent();

            // Comprobamos si el módulo es igual al que buscamos.
            if (name.equalsIgnoreCase(moduleName)) {
                // Muestra su información.
                System.out.println("Nombre: " + name);
                System.out.println("Horas: " + module.getElementsByTagName("hours").item(0).getTextContent());
                System.out.println("Tiempo: " + module.getElementsByTagName("time").item(0).getTextContent());
                System.out.println("Estudiante: " + module.getElementsByTagName("student").item(0).getTextContent());
                encontrado = true;
                return;
            }
        }

        // Imprimimos un mensaje si no lo hemos encontrado
        if (!encontrado) {
            System.out.println("El modulo " + moduleName + " no se ha encontrado");
        }

    }

    /**
     * Pregunta el nombre del módulo y te lo devuelve
     *
     * @return Un string que representa el nombre del módulo
     */
    public static String preguntarBuscar() {

        // Creamos un nuevo Scanner y preguntamos el módulo
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del módulo a buscar: ");
        String moduleName = scanner.nextLine();
        // Cerramos el scanner
        scanner.close();
        return moduleName;
    }
}

/**
 * Clase principal que ejecuta el programa
 */
public class XMLProgram {

    /**
     *
     * Ejecuta el programa, si tenemos la opcion write escribe en el fichero xml, si usamos la read lo lee y si ponemos search nos preguntara por un módulo en concreto y nos devolvera sus datos si existe. Si ponemos una opción inválida nos aparecera un mensaje indicandonoslo.
     *
     * @param args Los argumentos de línea de comandos que determinan la operación a realizar y los archivos XML involucrados.
     * @throws IOException Si ocurre un error de entrada o salida al trabajar con archivos.
     * @throws SAXException Si ocurre un error de análisis SAX al procesar un archivo XML.
     * @throws ParserConfigurationException Si ocurre un error de configuración del analizador XML.
     * @throws FileNotFoundException Si el archivo XML especificado no se encuentra.
     * @throws TransformerException Si ocurre un error durante la transformación XML.
     */
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, FileNotFoundException, TransformerException {
        fileXML dom = new fileXML();

        try {
            // Ejecutar si es read.
            if (args[0].equalsIgnoreCase("read")) {
                Document doc = dom.OpenXML(args[1]);
                dom.printModules(doc.getDocumentElement());
            } // Ejecutar si es write
            else if (args[0].equalsIgnoreCase("write")) {
                File file = new File(args[1]);
                if (!file.exists()) {
                    file.createNewFile();
                }
                dom.writeModules(file);
            } // Ejecutar si es search
            else if (args[0].equalsIgnoreCase("search")) {
                String moduleName = fileXML.preguntarBuscar();
                Document doc = dom.OpenXML(args[1]);
                dom.findModule(moduleName, doc);
            } // Ejecutar si no es valido
            else {
                System.out.println("Argumento inválido Posibles argumentos: read | write | search");
            }
        } catch (IOException | ParserConfigurationException | TransformerException | SAXException e) {
            System.out.println("Numero de argumentos invalidos (opcion archivo.xml) Error: " + e);
        }
    }
}
