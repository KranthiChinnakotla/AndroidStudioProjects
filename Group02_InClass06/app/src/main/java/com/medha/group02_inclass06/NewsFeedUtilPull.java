package com.medha.group02_inclass06;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by tejakanchinadam on 2/22/16.
 */
public class NewsFeedUtilPull {

    static public class NewsFeedPullParser{
        //Person person;
        static ArrayList<NewsFeed> NewsFeedParser(InputStream in) throws XmlPullParserException, IOException {
            Log.d("demo", "PULL");
            XmlPullParser parser= XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(in,"UTF-8");
            NewsFeed newsFeed = null;
            ArrayList<NewsFeed> newsFeedArrayList=new ArrayList<>();
            int event=parser.getEventType();
            Log.d("test",parser.toString());
            int toggle = 0;
            while (event!=XmlPullParser.END_DOCUMENT){
                switch (event){

                    case XmlPullParser.START_TAG:
                        if(parser.getName().equals("item")) {
                            newsFeed = new NewsFeed();
                        toggle=1;}
                            if(toggle==1) {
                                if (parser.getName().equals("title")) {
                                    newsFeed.setTitle(parser.nextText());
                                } else if (parser.getName().equals("description")) {
                                    newsFeed.setDescription(parser.nextText().trim());
                                }
                                else if (parser.getName().equals("pubDate")) {
                                    newsFeed.setPubDate(parser.nextText().trim());
                                }
                            }
                            //if(parser.getName().equals(""))


                        break;


                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("item")){
                            newsFeedArrayList.add(newsFeed);
                            newsFeed=null;
                            toggle = 0;
                        }

                    default:break;

                }
                event=parser.next();
            }

            return newsFeedArrayList;


        }

    }




}
