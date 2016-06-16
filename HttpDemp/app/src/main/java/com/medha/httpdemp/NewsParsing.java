package com.medha.httpdemp;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Prathyusha on 3/21/16.
 */
public class NewsParsing {

    static public class PullparseNews {
        static private String safeNextText(XmlPullParser parser)
                throws XmlPullParserException, IOException {
            String result = parser.nextText();
            if (parser.getEventType() != XmlPullParser.END_TAG) {
                parser.nextTag();
            }
            return result;
        }

        static public ArrayList<News> parsingForecast(InputStream in) throws XmlPullParserException, IOException {

            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(in, "UTF-8");
            News news = null;
            long subDate;
            ArrayList<News> newsList = new ArrayList<News>();
            int event = parser.getEventType();
            String direction = null;
            int count = 0;
            while (event != XmlPullParser.END_DOCUMENT) {
                switch (event) {
                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals("item")) {
                            news = new News();
                        } else if (parser.getName().equals("title")) {
                            String text = safeNextText(parser);
                            if(text.contains("VIDEO"))
                            news.setTitle(text.trim());
                        } else if (parser.getName().equals("pubDate")) {
                            String text = safeNextText(parser);
                            news.setPubdate(text.trim());
                            news.setSubDate(Long.parseLong(text.trim().substring(5, 7)));
                        } else if (parser.getName().equals("media:thumbnail")) {
                            news.setImageUrl(parser.getAttributeValue(null, "url"));
                        }
                        break;
                    case XmlPullParser.END_TAG:

                        if (parser.getName().equals("item"))
                            newsList.add(news);
                        break;
                    default:
                        break;
                }
                event = parser.next();
            }

            return newsList;
        }


    }
}
