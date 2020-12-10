package fr.ensicaen.genielogiciel.mvp.model.course;

import fr.ensicaen.genielogiciel.mvp.model.boat.Cap;
import org.json.JSONObject;

public class Weather {
    private float _windSpeed;
    private Cap _windDirection;
    private double _latitude;
    private double _longitude;

    public Weather() {
        _latitude = 0;
        _longitude = 0;
    }

    public void loadWindInfo(String json) {
        JSONObject jObject  = new JSONObject(json);
        JSONObject cityInfo = jObject.getJSONObject("city_info");
        JSONObject condition = jObject.getJSONObject("current_condition");

        _latitude = cityInfo.getDouble("latitude");
        _longitude = cityInfo.getDouble("longitude");
        _windSpeed = condition.getFloat("wnd_spd");

        switch(condition.getString("wnd_dir")) {
            case "N":
                _windDirection = Cap.NORTH;
                break;
            case "S":
                _windDirection = Cap.SOUTH;
                break;
            case "E":
                _windDirection = Cap.EAST;
                break;
            default:
                _windDirection = Cap.WEST;
                break;
        }
    }

    public float getWindSpeed() {
        return _windSpeed;
    }


    public Cap getWindDirection() {
        return _windDirection;
    }

    @Override
    public String toString() {
        return "Weather{" + "_wind_speed=" + _windSpeed +
                ", _wind_direction='" + _windDirection + '\'' +
                ", _latitude='" + _latitude + '\'' +
                ", _longitude='" + _longitude + '\'' +
                '}';
    }
}
