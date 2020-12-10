package fr.ensicaen.genielogiciel.mvp.model.course;

import fr.ensicaen.genielogiciel.mvp.model.Vector;
import fr.ensicaen.genielogiciel.mvp.model.boat.Boat;

import java.util.Observable;
import java.util.Observer;

import static java.lang.Math.abs;

public class Buoy implements Observer {
    private final int VALIDATION_ZONE_WIDTH = 10;
    private final Vector _position;
    private final Vector _limit;
    private boolean _isValidated;

    public Buoy( Vector position, Vector limit ) {
        _position = position;
        _limit = limit;
        _isValidated = false;
    }

    public boolean isNotValidated() {
        return !_isValidated;
    }

    public void setValidated( boolean bool ) {
        _isValidated = bool;
    }

    public Vector getPosition() {
        return _position;
    }

    @Override
    public void update(Observable o, Object arg) {
        Boat regatta = (Boat) o;
        Vector regattaPosition = regatta.getPosition();

        if (boatIsInTheVerticalValidationZone(regattaPosition)
                || boatIsInTheHorizontalValidationZone(regattaPosition)) {
            _isValidated = true;
        }
    }

    private boolean boatIsInTheHorizontalValidationZone(Vector regattaPosition) {
        return (_position.x == _limit.x) && ((regattaPosition.y > _position.y && regattaPosition.y < _limit.y)
                || (regattaPosition.y < _position.y && regattaPosition.y > _limit.y))
                && (abs(regattaPosition.x - _position.x) < VALIDATION_ZONE_WIDTH);
    }

    private boolean boatIsInTheVerticalValidationZone(Vector regattaPosition) {
        return (_position.y == _limit.y) && ((regattaPosition.x > _position.x && regattaPosition.x < _limit.x)
                || (regattaPosition.x < _position.x && regattaPosition.x > _limit.x))
                && (abs(regattaPosition.y - _position.y) < VALIDATION_ZONE_WIDTH);
    }
}


