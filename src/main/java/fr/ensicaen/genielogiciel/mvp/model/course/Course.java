package fr.ensicaen.genielogiciel.mvp.model.course;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.io.IOException;

public class Course {
    private int wind_speed;
    private char wind_direction;

    public void load_weather() throws IOException {
        BufferedReader in;
        String inputLine;
        StringBuffer content;
        int status;
        String latitude = "49.283";
        String longitude = "-0.25";
        String urlPath = "https://www.prevision-meteo.ch/services/json/lat=" + latitude + "lng=" + longitude;


        URL url = new URL(urlPath);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);

        status = con.getResponseCode();

        if(status == 200) {
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            System.out.println(content);
            in.close();
        }

        con.disconnect();
    }
}
