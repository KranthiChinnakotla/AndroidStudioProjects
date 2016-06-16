package com.medha.inclass05spring15;

import android.util.Xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Prathyusha on 2/21/16.
 */
public class ImageUtils {
    static public class ImageParser extends DefaultHandler{

        ArrayList<String> imageUrls;
        StringBuilder innerText;
        Photos photo;
        static public  ArrayList<String> parser (InputStream in) throws IOException, SAXException {

            ImageParser parser = new ImageParser();
            Xml.parse(in, Xml.Encoding.UTF_8,parser);
            return parser.getImageUrls();

        }

        public ArrayList<String> getImageUrls() {
            return imageUrls;
        }

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            imageUrls = new ArrayList<String>();
            innerText = new StringBuilder();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
             if(localName.equals("photos")){
                 photo = new Photos();}

            else if(localName.equals("photo"))
                 photo.setPhotoUrl(attributes.getValue("url_m"));
             }


        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            imageUrls.add(photo.getPhotoUrl());

        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);


        }
    }
}
