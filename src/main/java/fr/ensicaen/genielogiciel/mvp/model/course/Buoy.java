package fr.ensicaen.genielogiciel.mvp.model.course;

import fr.ensicaen.genielogiciel.mvp.model.Vector;
import fr.ensicaen.genielogiciel.mvp.model.boat.Boat;

import java.util.Observable;
import java.util.Observer;

import static java.lang.Math.abs;

public class Buoy implements Observer {
    private final Vector _position;
    private final Vector _limit;
    private boolean _isValidated;

    public Buoy( Vector position, Vector limit ) {
        _position = position;
        _limit = limit;
        _isValidated = false;
    }

    public boolean isValidated() {
        return _isValidated;
    }

    public void setValidated( boolean bool ) {
        _isValidated = bool;
    }

    public Vector getPosition() {
        return _position;
    }

    @Override
    public void update( Observable o, Object arg ) {
        Boat regatta = ((Boat) o);
        Vector v = regatta.getPosition();
        if (_position.y == _limit.y) {
            if ((v.x > _position.x && v.x < _limit.x) || (v.x < _position.x && v.x > _limit.x)) {
                if (abs(v.y - _position.y) < 10) {
                    _isValidated = true;
                }
            }
        } else {
            if ((v.y > _position.y && v.y < _limit.y) || (v.y < _position.y && v.y > _limit.y)) {
                if (abs(v.x - _position.x) < 10) {
                    _isValidated = true;
                }
            }
        }
    }
}


