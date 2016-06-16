package com.medha.itunestopgrossing;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Prathyusha on 6/14/16.
 */
public class ParseItunes {
    static public class PullItunes{
        static ArrayList<TopApps> parseitNow(InputStream in) throws XmlPullParserException, IOException {
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(in,"UTF-8");
            int event = parser.getEventType();
            TopApps topApps = null;
            ArrayList<TopApps> topAppsArrayList = new ArrayList<>();

            while(event != XmlPullParser.END_DOCUMENT){
                switch(event){
                    case XmlPullParser.START_TAG:
                        if(parser.getName().equals("entry")){
                            topApps = new TopApps();
                        }
                        if(parser.getName().equals("im:name")){
                            topApps.setName(parser.nextText().trim());
                        }
                        if(parser.getName().equals("im:artist")){
                            topApps.setDev_name(parser.nextText().trim());
                        }
                        if(parser.getName().equals("im:price")){
                            topApps.setPrice(Double.parseDouble(parser.getAttributeValue(null,"amount")));
                        }
                        if(parser.getName().equals("im:releaseDate")){
                            topApps.setRel_date(parser.getAttributeValue(null,"label"));
                        }
                        if(parser.getName().equals("category")){
                            topApps.setCategory(parser.getAttributeValue(null,"label"));
                        }

                        if(parser.getName().equals("im:image")){
                            if(parser.getAttributeValue(null,"height").equals("53")){
                                topApps.setImage(parser.nextText().trim());
                            }
                        }

                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("entry")){
                            topAppsArrayList.add(topApps);
                        }
                        break;
                    default:
                        break;
                }
                event = parser.next();
            }

            return topAppsArrayList;
        }
    }
}
