package fr.ensicaen.genielogiciel.mvp.model.course;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;


public class WeatherTest {
    private String _latitude = "49.283";
    private String _longitude = "-0.25";

    @Test
    public void load_weather_info() {
        // Not testable
    }

    @Test
    public void set_wind_info() {
        Weather weather = new Weather(_latitude, _longitude);
        String json_data;
        float wind_speed;
        String wind_direction;
        try {
            json_data = weather.load_weather_info();
            wind_speed = weather.get_wind_speed();
            wind_direction = weather.get_wind_direction();
            weather.set_wind_info(json_data);
            assertNotEquals(null, wind_speed, weather.get_wind_speed());
            assertNotEquals(null, wind_direction, weather.get_wind_direction());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
