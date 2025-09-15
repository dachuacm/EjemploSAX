package org.example.DOM;

import org.example.Empleado;
import org.example.Main;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserDom {

    private DocumentBuilderFactory factory;

    private DocumentBuilder builder;
    private List<Empleado> lista;

    private Document document;

    public Document getDocument() {
        return document;
    }

    public ParserDom() {
        factory= DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(Main.class.getResourceAsStream("/empleados.xml"));
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }


    }

    public List<Empleado> recuperarEmpleados()
    {
        lista=new ArrayList<Empleado>();
        //factory= DocumentBuilderFactory.newInstance();

          //  builder = factory.newDocumentBuilder();

            //Document document = builder.parse(Main.class.getResourceAsStream("/empleados.xml"));
            Element rootElement = document.getDocumentElement();

            NodeList nodeList = rootElement.getChildNodes();

            for (int i = 0; i < nodeList.getLength(); i++) {

                Empleado e=new Empleado();
                Node node = nodeList.item(i);

                if(node.getNodeType()==Node.ELEMENT_NODE){
                Element dato=(Element) node;


                e.setId(Integer.parseInt(dato.getAttributeNode("id").getValue()));

                //e.setEdad(Integer.parseInt(dato.getElementsByTagName("age").item(0).getTextContent()));
                e.setNombre(dato.getElementsByTagName("name").item(0).getTextContent());
                e.setRol(dato.getElementsByTagName("role").item(0).getTextContent());
                e.setGenero(dato.getElementsByTagName("gender").item(0).getTextContent());
                lista.add(e);
                }

            }



        return lista;
    }


    public void agregarEmpleado(Empleado e){

        Element rootElement = document.getDocumentElement();

        Element nuevo=document.createElement("Employee");
        nuevo.setAttribute("id",Integer.toString(e.getId()));

        Element dato=document.createElement("name");
        dato.setTextContent(e.getNombre());
        nuevo.appendChild(dato);

        dato=document.createElement("age");
        dato.setTextContent(Integer.toString(e.getEdad()));
        nuevo.appendChild(dato);

        dato=document.createElement("role");
        dato.setTextContent(e.getRol());
        nuevo.appendChild(dato);

        dato=document.createElement("gender");
        dato.setTextContent(e.getGenero());
        nuevo.appendChild(dato);

        rootElement.appendChild(nuevo);


    }

    // Elimina un empleado por ID
    public void eliminarEmpleado(Empleado e) {
        Element rootElement = document.getDocumentElement();
        NodeList empleados = rootElement.getElementsByTagName("Employee");

        for (int i = 0; i < empleados.getLength(); i++) {
            Element emp = (Element) empleados.item(i);
            if (Integer.parseInt(emp.getAttribute("id")) == e.getId()) {
                rootElement.removeChild(emp);
                break;
            }
        }
    }

    public void guardarArchivo(){


    TransformerFactory fac=TransformerFactory.newInstance();

    try {
        Transformer transformer=fac.newTransformer();
        DOMSource source=new DOMSource(document);
        StreamResult result=new StreamResult(new File("archivo.xml"));
        transformer.transform(source,result);
    } catch (TransformerConfigurationException e) {
        throw new RuntimeException(e);
    } catch (TransformerException e) {
        throw new RuntimeException(e);
    }

}
}
