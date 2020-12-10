package fr.ensicaen.genielogiciel.mvp.model.course;

import fr.ensicaen.genielogiciel.mvp.model.boat.Cap;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;


public class WeatherTest {
    private String _latitude = "49.283";
    private String _longitude = "-0.25";
    private String _url = "https://www.prevision-meteo.ch/services/json/lat=" + _latitude + "lng=" + _longitude;

    @Test
    public void loadWeatherInfo() {
        Weather weather = new Weather();
        String json_data;
        float wind_speed;
        Cap wind_direction;
        try {
            json_data = WeatherLoader.loadWeatherInfo(_latitude, _longitude);
            wind_speed = weather.getWindSpeed();
            wind_direction = weather.getWindDirection();
            weather.loadWindInfo(json_data);
            assertNotEquals(null, wind_speed, weather.getWindSpeed());
            assertNotEquals(null, wind_direction, weather.getWindDirection());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
