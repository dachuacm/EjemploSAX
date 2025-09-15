package org.example.DOM;

import org.example.Empleado;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.*;
import java.util.ArrayList;
import java.util.List;

public class BuscadorXPath {
    XPathFactory xPathFactory;
    XPath xpath;
    Document documento;

    public String resString;

    public BuscadorXPath(Document docuemnto) {
        this.documento = docuemnto;
        xPathFactory=XPathFactory.newInstance();
        xpath=xPathFactory.newXPath();

    }

    public List<String> busqueda2(String criterio){
        List<String> salida=new ArrayList<String>();
        try {
            XPathExpression expr= xpath.compile(criterio);
            NodeList nodos= (NodeList) expr.evaluate(documento,XPathConstants.NODESET);
            for (int i = 0; i < nodos.getLength(); i++) {
                Node node = nodos.item(i);
                salida.add(node.getTextContent());
            }
        } catch (XPathExpressionException e) {

            throw new RuntimeException(e);
        }

        return salida;
    }
    public List<Empleado> busqueda1(String criterio){
        List<Empleado>lista=new ArrayList<Empleado>();
        try {
            XPathExpression expr= xpath.compile(criterio);

            NodeList resultado=
                    (NodeList)expr.evaluate(documento,XPathConstants.NODESET);

            for (int i = 0; i < resultado.getLength(); i++) {
                Node node = resultado.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    lista.add(convierte(node));
                }
            }
            //System.out.println(resultado.getLength());

        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }


private Empleado convierte(Node node) {


    Empleado e = new Empleado();



        Element dato = (Element) node;


        e.setId(Integer.parseInt(dato.getAttributeNode("id").getValue()));

        e.setEdad(Integer.parseInt(dato.getElementsByTagName("age").item(0).getTextContent()));
        e.setNombre(dato.getElementsByTagName("name").item(0).getTextContent());
        e.setRol(dato.getElementsByTagName("role").item(0).getTextContent());
        e.setGenero(dato.getElementsByTagName("gender").item(0).getTextContent());



           return e;
}














}
