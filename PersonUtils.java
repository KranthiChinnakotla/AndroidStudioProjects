package com.medha.saxparsingdemo;

import android.util.Xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Prathyusha on 2/20/16.
 */
public class PersonUtils {
    static public class SaxParse extends DefaultHandler {
        ArrayList<Persons> personList;
        Persons persn;
        StringBuilder xmlInnertext;

        public ArrayList<Persons> getPersonList() {
            return personList;
        }

        static public ArrayList<Persons> parseIt (InputStream in) throws IOException, SAXException {

            SaxParse parser = new SaxParse();
            Xml.parse(in,Xml.Encoding.UTF_8,parser);
            return parser.getPersonList();
        }

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            personList = new ArrayList<Persons>();
            xmlInnertext = new StringBuilder();

        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            if(localName.equals("person")){
                persn = new Persons();
                persn.setId(attributes.getValue("id"));
            }

        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            if(localName.equals("person")){
                personList.add(persn);
            }
            else if (localName.equals("name")){
                persn.setName(xmlInnertext.toString().trim());
            }
            else if(localName.equals("age")){
                persn.setAge(xmlInnertext.toString().trim());
            }
            else if(localName.equals("department")){
                persn.setDepartment(xmlInnertext.toString().trim());
            }

            xmlInnertext.setLength(0);
        }



        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            xmlInnertext.append(ch,start,length);

        }
    }
}
