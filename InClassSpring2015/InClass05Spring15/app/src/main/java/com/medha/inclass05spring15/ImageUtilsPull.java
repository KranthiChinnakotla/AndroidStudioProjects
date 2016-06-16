package com.medha.inclass05spring15;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Prathyusha on 2/21/16.
 */
public class ImageUtilsPull {
    static public class PhotoPullParser{
        static ArrayList<String> photoList;
        static Photos photo;
        static ArrayList<String> parsePhotos(InputStream in) throws XmlPullParserException, IOException {
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(in,"UTF-8");
            photoList = new ArrayList<String>();
            int event = parser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT){
                switch (event){
                    case XmlPullParser.START_TAG:
                        if(parser.getName().equals("rsp")){
                        }else if (parser.getName().equals("photos")){

                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("photo")){
                            photoList.add(photo.getPhotoUrl());
                        }
                        break;
                    default:
                        break;
                }

                event = parser.next();
            }
            return photoList;

        }

    }
}
