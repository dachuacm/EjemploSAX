package org.example;

import org.example.DOM.BuscadorXPath;
import org.example.DOM.ParserDom;
import org.example.SAX.ParserSax;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
List<Empleado> empList=null;

ParserDom parser=new ParserDom();
    //empList=new ParserSax().recuperarEmpleados();

BuscadorXPath buscador=new BuscadorXPath(parser.getDocument());

        System.out.println(buscador.busqueda1("/Employees/Employee[@id=4]"));
        System.out.println("Roles");
        System.out.println(buscador.busqueda2("/Employees/Employee[last()]/role"));
    }
}