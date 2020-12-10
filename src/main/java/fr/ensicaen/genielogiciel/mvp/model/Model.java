package fr.ensicaen.genielogiciel.mvp.model;

import fr.ensicaen.genielogiciel.mvp.model.boat.Cap;
import fr.ensicaen.genielogiciel.mvp.model.boat.Regalata;
import fr.ensicaen.genielogiciel.mvp.model.course.Path;
import fr.ensicaen.genielogiciel.mvp.model.course.Weather;
import fr.ensicaen.genielogiciel.mvp.model.course.WeatherLoader;

import java.io.IOException;

public class Model {
    private Path _path;
    private Regalata _regalata;
    private String _nickname;
    private Vector _regalataPosition;
    private Weather _weather;

    public Model() {
        _weather = new Weather();
        try {
            String json_data = WeatherLoader.loadWeatherInfo("49.283", "-0.25");
            _weather.loadWindInfo(json_data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        _regalata = new Regalata(_weather.getWindDirection(),_weather.getWindSpeed());
        _regalataPosition = _regalata.getPosition();

        _path = new Path();
    }

    public String getNickname() {
        return _nickname;
    }

    public void setNickname(String nickname) {
        _nickname = nickname;
    }
    
    public void initPosition(float x, float y) {
        _regalata.setPosition(new Vector(x, y));
        _regalataPosition = _regalata.getPosition();
    }
    
    public float getOrientation() {
        return _regalata.getOrientation();
    }

    public Vector getRegalataPosition() {
        return _regalataPosition;
    }

    public void turnBoatLeft() {
        _regalata.changeOrientation(Cap.WEST);
    }

    public void turnBoatRight() {
        _regalata.changeOrientation(Cap.EAST);
    }
    
    public void movingForward() {
        _regalata.windAction();
        _regalata.changePosition();

    }
}
