package fr.ensicaen.genielogiciel.mvp.model.course;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherLoader {
    private String _url;

    public WeatherLoader(String url) {
        _url = url;
    }

    public String load_weather_info() throws IOException {
        BufferedReader in;
        String inputLine;
        StringBuilder content = new StringBuilder();
        int status;

        URL url = new URL(_url);
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
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
        }
        con.disconnect();
        return content.toString();
    }
}
