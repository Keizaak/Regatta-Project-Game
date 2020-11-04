package fr.ensicaen.genielogiciel.mvp.model.course;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Course {
    private String _weather_json;
    private float _wind_speed;
    private String _wind_direction;

    public void load_weather_info(String latitude, String longitude) throws IOException {
        BufferedReader in;
        String inputLine;
        StringBuffer content;
        int status;
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
            _weather_json = content.toString();
            in.close();
        }
        con.disconnect();
    }

    public void load_wind_info(String json) {
        JSONObject jObject  = new JSONObject(json);
        JSONObject data = jObject.getJSONObject("current_condition");
        _wind_speed = data.getFloat("wnd_spd");
        _wind_direction = data.getString("wnd_dir");
    }

    public float get_wind_speed() {
        return _wind_speed;
    }

    public String get_wind_direction() {
        return  _wind_direction;
    }
}
