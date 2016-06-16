package com.medha.inclass0515;




import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by Prathyusha on 2/15/16.
 */
public class RequestParams {

    String method,baseUrl;
    HashMap<String, String> params = new HashMap<>();

    public RequestParams(String method, String baseUrl) {
        this.method = method;
        this.baseUrl = baseUrl;
    }

    public void addParam(String key,String value){
        params.put(key, value);
    }

    public String getEncodedParams() throws UnsupportedEncodingException {

        StringBuilder sb = new StringBuilder();

        for (String key: params.keySet()){
            String value = URLEncoder.encode(params.get(key),"UTF-8");
            sb.append(value);
        }

        return sb.toString();
    }

    public String getEncodedUrl() throws UnsupportedEncodingException {
        return this.baseUrl+ "?"+ getEncodedParams();
    }

    public HttpURLConnection setupConnection() throws IOException {

        if (method.equals("GET")){
            try {
                URL url = new URL(getEncodedUrl());
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod(method);
                return con;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }else{
            URL url = new URL(this.baseUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(method);
            con.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
            writer.write(getEncodedParams());
            writer.flush();
            return con;
        }

        return null;
    }


}
