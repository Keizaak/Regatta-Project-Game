package fr.ensicaen.genielogiciel.mvp.model.course;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherLoader {
    public static String loadWeatherInfo(String latitude, String longitude) throws IOException {
        BufferedReader in;
        String inputLine;
        StringBuilder content = new StringBuilder();
        int status;
        String url = "https://www.prevision-meteo.ch/services/json/lat=" + latitude + "lng=" + longitude;
        URL request_url = new URL(url);
        HttpURLConnection con = (HttpURLConnection)request_url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);

        status = con.getResponseCode();
        if(successfulRequest(status)) {
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
        }
        con.disconnect();
        return content.toString();
    }

    private static boolean successfulRequest(int status) {
        return status == 200;
    }
}
