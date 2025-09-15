package org.example.SAX;

import org.example.Empleado;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class XMLHandler extends DefaultHandler {

    private List<Empleado> empleados;
    private Empleado empleado;

    private StringBuilder data=null;

    boolean bEdad=false;
    boolean bNombre=false;
    boolean bGenero=false;
    boolean bRol=false;

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equalsIgnoreCase("Employee")) {
            String id = attributes.getValue("id");
            empleado = new Empleado();
            empleado.setId(Integer.parseInt(id));
            if (empleados == null) {
                empleados = new ArrayList<Empleado>();
            }
        }else if(qName.equalsIgnoreCase("name")){
            bNombre=true;
        }else if(qName.equalsIgnoreCase("gender")){
            bGenero=true;
        }else if(qName.equalsIgnoreCase("role")){
            bRol=true;
        }
        else if(qName.equalsIgnoreCase("age")){
            bEdad=true;
        }

        data=new StringBuilder();


    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(bEdad){
            empleado.setEdad(Integer.parseInt(data.toString()));
            bEdad=false;

        }
        else  if(bNombre){
            empleado.setNombre(data.toString());
            bNombre=false;
        }
        else  if(bGenero){
            empleado.setGenero(data.toString());
            bGenero=false;
        }
        else  if(bRol){
            empleado.setRol(data.toString());
            bRol=false;
        }

        if(qName.equalsIgnoreCase("Employee")){
            empleados.add(empleado);
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        data.append(new String(ch,start,length));
    }
}
