package fr.ensicaen.genielogiciel.mvp.model.boat;


public class Regalata extends Boat {

    public Regalata() {
        _windOrientation = Cap.NORTH;
        _windSpeed = 1;
        _position = new Vector();
        _orientation = getDirection(Cap.NORTH);
    }

    public Vector getDirection(Cap cap) {
        switch (cap) {
            case EAST:
                return new Vector(1, 0);
            case WEST:
                return new Vector(-1, 0);
            default:
                return new Vector(0, 1);
        }
    }

    public Vector getPosition() {
        return _position;
    }

    public void changeOrientation(Cap cap) {
        _orientation = getDirection(cap);
        changePosition();
    }

    public Vector changePosition() {
        _position._x += _orientation._x;
        _position._y += _orientation._y;
        System.out.println("Position bato " + _position._x + " " + _position._y);
        return _position;
    }

    /* TODO: function moving_forward (calling indefinitely changePosition) */
}
