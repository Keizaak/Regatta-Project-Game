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
    private boolean _replayEnded;

    public Model() {
        _replayEnded = false;

        loadWeather();

        _regalata = new Regalata(_weather.getWindDirection(),_weather.getWindSpeed());
        _regalataPosition = _regalata.getPosition();

        createPath();
    }

    private void loadWeather() {
        _weather = new Weather();
        try {
            String latitude = "49.283";
            String longitude = "-0.25";
            String json_data = WeatherLoader.loadWeatherInfo(latitude, longitude);
            _weather.loadWindInfo(json_data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createPath() {
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
        for (Buoy b : _path.getBuoys()) {
            if (b.isNotValidated()) {
                return false;
            }
        }
        return true;
    }

    public void movingForward() {
        _regalata.windAction();
        _regalata.changePosition();
    }

    public void replay() {
        _replayEnded = false;
        for (Buoy b : _path.getBuoys()) {
            b.setValidated(false);
        }
        _regalata = new Regalata(_weather.getWindDirection(),_weather.getWindSpeed());
        _regalataPosition = _regalata.getPosition();
    }

    public void setReplayEnded(boolean b) {
        _replayEnded = b;
    }

    public boolean hasReplayEnded() {
        return _replayEnded;
    }
}
