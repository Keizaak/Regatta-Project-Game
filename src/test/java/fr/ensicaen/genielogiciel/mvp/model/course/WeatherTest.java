package fr.ensicaen.genielogiciel.mvp.model.course;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;


public class WeatherTest {
    private String _latitude = "49.283";
    private String _longitude = "-0.25";
    private String _url = "https://www.prevision-meteo.ch/services/json/lat=" + _latitude + "lng=" + _longitude;

    @Test
    public void load_weather_info() {
        Weather weather = new Weather(_latitude, _longitude);
        WeatherLoader weatherLoader = new WeatherLoader(_url);
        String json_data;
        float wind_speed;
        String wind_direction;
        try {
            json_data = weatherLoader.load_weather_info();
            wind_speed = weather.get_wind_speed();
            wind_direction = weather.get_wind_direction();
            weather.load_wind_info(json_data);
            assertNotEquals(null, wind_speed, weather.get_wind_speed());
            assertNotEquals(null, wind_direction, weather.get_wind_direction());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
