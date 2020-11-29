package fr.ensicaen.genielogiciel.mvp.model.course;

import org.json.JSONObject;

public class Weather {
    private float _wind_speed;
    private String _wind_direction;
    private String _latitude;
    private String _longitude;

    public Weather(String latitude, String longitude) {
        _latitude = latitude;
        _longitude = longitude;
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
