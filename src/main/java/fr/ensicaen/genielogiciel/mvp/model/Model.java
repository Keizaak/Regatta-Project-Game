package fr.ensicaen.genielogiciel.mvp.model;

import fr.ensicaen.genielogiciel.mvp.model.boat.Cap;
import fr.ensicaen.genielogiciel.mvp.model.boat.Regalata;
import fr.ensicaen.genielogiciel.mvp.model.course.Buoy;
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
    private boolean _gameFinished;

    public Model() {
        _gameFinished = false;
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
        _path.loadPath();
        for (Buoy b : _path.getBuoys()) {
            _regalata.addObserver(b);
        }
    }

    public String getNickname() {
        return _nickname;
    }

    public Path getPath() {
        return _path;
    }

    public void setNickname(String nickname) {
        _nickname = nickname;
    }
    
    public void initPosition(float x, float y) {
        _regalata.setPosition(new Vector(x, y));
        _regalataPosition = _regalata.getPosition();
    }

    public Weather getWeather() {
        return _weather;
    }

    public Cap getBoatDirection() {
        return _regalata.getDirection();
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

    public boolean isGameFinished() {
        boolean res = true;
        for (Buoy b : _path.getBuoys()) {
            if (!b.isValidated()) {
                return false;
            }
        }
        return true;
    }

    public void movingForward() {
        _regalata.windAction();
        _regalata.changePosition();
    }
}
