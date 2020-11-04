package fr.ensicaen.genielogiciel.mvp.model.course;

import org.junit.Test;

import java.io.IOException;


public class WeatherTest {
    @Test
    public void load_weather_info() {
        // Not testable
    }

    @Test
    public void load_wind_info() {
        Weather weather = new Weather("49.283", "-0.25");
        String json_data;
        try {
            json_data = weather.load_weather_info();
            weather.load_wind_info(json_data);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void get_wind_speed() {
    }

    @Test
    public void get_wind_direction() {
    }
}
