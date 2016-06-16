package com.inclass.raja.homework5;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Prathyusha on 3/7/16.
 */
public class ParseWeather {
    static public class PullParseWeather {
        static public ArrayList<Weather> parsing(InputStream in) throws XmlPullParserException, IOException {

            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(in, "UTF-8");
            Weather weather = null;
            ArrayList<Weather> weatherList = new ArrayList<Weather>();
            int event = parser.getEventType();
            String direction = null;
            while (event != XmlPullParser.END_DOCUMENT) {
                switch (event) {
                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals("forecast")) {
                            weather = new Weather();
                        } else if (parser.getName().equals("hour")) {
                            weather.setTime(parser.nextText().trim());
                        } else if (parser.getName().equals("temp")) {
                            int next = parser.nextTag();
                            if (next == XmlPullParser.START_TAG) {
                                if (parser.getName().equals("english"))
                                    weather.setTemperature(parser.nextText().trim());
                            }
                        } else if (parser.getName().equals("dewpoint")) {
                            int next = parser.nextTag();
                            if (next == XmlPullParser.START_TAG) {
                                if (parser.getName().equals("english"))
                                    weather.setDewpoint(parser.nextText().trim());
                            }
                        } else if (parser.getName().equals("icon_url")) {
                            weather.setIconUrl(parser.nextText().trim());
                        } else if (parser.getName().equals("wspd")) {
                            int next = parser.nextTag();
                            if (next == XmlPullParser.START_TAG) {
                                if (parser.getName().equals("english"))
                                    weather.setWindSpeed(parser.nextText().trim());
                            }
                        } else if (parser.getName().equals("dir")) {
                            direction = parser.nextText().trim();
                        }
                        else if(parser.getName().equals("degrees")){
                            weather.setWindDirection(parser.nextText().trim()+" " + direction);
                        }

                        else if (parser.getName().equals("wx")) {
                            weather.setClimateType(parser.nextText().trim());
                        } else if (parser.getName().equals("humidity")) {
                            weather.setHumidity(parser.nextText().trim());
                        } else if (parser.getName().equals("feelslike")) {
                            int next = parser.nextTag();
                            if (next == XmlPullParser.START_TAG) {
                                if (parser.getName().equals("english"))
                                    weather.setFeelsLike(parser.nextText().trim());
                            }
                        }
                        else if (parser.getName().equals("mslp")) {
                                int next = parser.nextTag();
                                if (next == XmlPullParser.START_TAG) {
                                    if (parser.getName().equals("english"))
                                        weather.setPressure(parser.nextText().trim());
                                }
                        }
                        else if(parser.getName().equals("condition")){
                            weather.setClouds(parser.nextText().trim());
                        }


                                break;
                                case XmlPullParser.END_TAG:
                                    if (parser.getName().equals("forecast"))
                                        weatherList.add(weather);
                                    break;
                                default:
                                    break;
                            }
                            event = parser.next();
                        }


                        return weatherList;
                }
            }

        }


