package org.example.SAX;

import org.example.Empleado;
import org.example.Main;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

public class ParserSax {
    List<Empleado> empList;
    SAXParserFactory saxParserFactor;

 public List<Empleado> recuperarEmpleados() {
    saxParserFactor = SAXParserFactory.newInstance();
     try {
         SAXParser parser = saxParserFactor.newSAXParser();
         XMLHandler handler = new XMLHandler();
         parser.parse(Main.class.getResourceAsStream("/empleados.xml"), handler);
         empList = handler.getEmpleados();

        /* for (Empleado emp : empList)
             System.out.println(emp);*/

     } catch (
             ParserConfigurationException e) {
         throw new RuntimeException(e);
     } catch (
             SAXException e) {
         throw new RuntimeException(e);
     } catch (
             IOException e) {
         throw new RuntimeException(e);
     }
     return empList;
 }
}
