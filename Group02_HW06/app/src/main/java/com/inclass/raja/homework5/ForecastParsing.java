package com.inclass.raja.homework5;

import android.util.Xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Prathyusha on 3/18/16.
 */
public class ForecastParsing {

    static public class PullparseForecast {
        static private String safeNextText(XmlPullParser parser)
                throws XmlPullParserException, IOException {
            String result = parser.nextText();
            if (parser.getEventType() != XmlPullParser.END_TAG) {
                parser.nextTag();
            }
            return result;
        }

        static public ArrayList<Forecast> parsingForecast(InputStream in) throws XmlPullParserException, IOException {

            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(in, "UTF-8");
            Forecast forecast = null;
            String formatDate;
            ArrayList<Forecast> forecastList = new ArrayList<Forecast>();
            int event = parser.getEventType();
            String direction = null;
            int count = 0;
            while (event != XmlPullParser.END_DOCUMENT) {
                switch (event) {
                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals("simpleforecast")) {
                            count = 1;
                        } else if (parser.getName().equals("forecastday")) {
                            forecast = new Forecast();
                        } else if (parser.getName().equals("pretty")) {
                            formatDate = safeNextText(parser);

                            forecast.setDate(formatDate.substring(15, 23));

                        } else if (parser.getName().equals("high")) {
                            int next = parser.nextTag();
                            if (next == XmlPullParser.START_TAG) {
                                if (parser.getName().equals("fahrenheit")) {
                                    String text = safeNextText(parser);
                                    forecast.setHighTemp(text.trim());
                                }
                            }
                        } else if (parser.getName().equals("low")) {
                            int next = parser.nextTag();
                            if (next == XmlPullParser.START_TAG) {
                                if (parser.getName().equals("fahrenheit")) {

                                    String text = safeNextText(parser);
                                    forecast.setLowTemp(text.trim());

                                }
                            }
                        } else if (parser.getName().equals("icon_url")) {
                            String text = safeNextText(parser);
                            if (forecast != null)
                                forecast.setIconUrl(text.trim());

                        } else if (parser.getName().equals("maxwind")) {
                            int next = parser.nextTag();
                            if (next == XmlPullParser.START_TAG) {
                                if (parser.getName().equals("mph")) {
                                    String text = safeNextText(parser);
                                    forecast.setMaxwindSpeed(text.trim());
                                }
                                next = parser.nextTag();


                            }
                            if (parser.getName().equals("kph")) {
                                try {
                                    next = parser.nextTag();
                                } catch (Exception e) {
                                    parser.next();
                                }
                            }

                            if (next == XmlPullParser.START_TAG) {
                                String text = null;
                                if (parser.getName().equals("dir")) {
                                    try {
                                        text = safeNextText(parser);
                                    } catch (Exception e) {
                                        forecast.setWindDirection(null);
                                    }
                                    forecast.setWindDirection(text.trim());
                                }
                            }
                        } else if (parser.getName().equals("avehumidity")) {
                            String text = safeNextText(parser);
                            forecast.setAvghumidity(text.trim());

                        } else if (parser.getName().equals("conditions")) {
                            String text = safeNextText(parser);
                            forecast.setClouds(text.trim());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        try {
                            if (parser.getName().equals("minhumidity"))
                                forecastList.add(forecast);
                        } catch (Exception e) {
                            break;
                        }
                        break;
                    default:
                        break;
                }
                event = parser.next();
            }

            return forecastList;
        }
    }
}





