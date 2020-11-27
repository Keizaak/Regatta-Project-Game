package fr.ensicaen.genielogiciel.mvp.model.course;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Weather {
    private float _wind_speed;
    private String _wind_direction;
    private String _latitude;
    private String _longitude;

    /**
     * Creates a Weather object given a latitude and a longitude
     * @param latitude latitude value
     * @param longitude longitude value
     */
    public Weather(String latitude, String longitude) {
        _latitude = latitude;
        _longitude = longitude;
    }

    /**
     * Load weather information from a weather API
     * @return JSON string with weather information
     * @throws IOException
     */
    public String load_weather_info() throws IOException {
        BufferedReader in;
        String inputLine;
        StringBuilder content = new StringBuilder();;
        int status;
        String urlPath = "https://www.prevision-meteo.ch/services/json/lat=" + _latitude + "lng=" + _longitude;

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
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
        }
        con.disconnect();
        return content.toString();
    }

    /**
     * Set wind and direction given a JSON string
     * @param json JSON formatted weather information
     */
    public void set_wind_info(String json) {
        JSONObject jObject  = new JSONObject(json);
        JSONObject data = jObject.getJSONObject("current_condition");
        _wind_speed = data.getFloat("wnd_spd");
        _wind_direction = data.getString("wnd_dir");
    }

    /**
     * Get the wind speed value
     * @return wind speed value
     */
    public float get_wind_speed() {
        return _wind_speed;
    }

    /**
     * Get the wind direction value
     * @return wind direction value
     */
    public String get_wind_direction() {
        return  _wind_direction;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Weather{");
        sb.append("_wind_speed=").append(_wind_speed);
        sb.append(", _wind_direction='").append(_wind_direction).append('\'');
        sb.append(", _latitude='").append(_latitude).append('\'');
        sb.append(", _longitude='").append(_longitude).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
