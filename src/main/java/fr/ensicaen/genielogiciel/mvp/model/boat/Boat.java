package fr.ensicaen.genielogiciel.mvp.model.boat;

public abstract class Boat {
    public enum Cap {
        EAST,
        WEST,
        NORTH,
        SOUTH
    }

    private Vector _position;
    private Cap _direction;
    private float _orientation;
    private Cap _windDirection;
    private float _windSpeed;

    protected Boat() {
        _position = new Vector();
        _direction = Cap.NORTH;
        _orientation = 270;
        _windDirection = Cap.SOUTH;
        _windSpeed = 1;
    }

    public Vector getPosition() {
        return _position;
    }

    public float getOrientation() {
        return _orientation;
    }

    public void setPosition(Vector position) {
        _position = position;
    }

    public Vector getDirection(Cap cap) {
        switch (cap) {
            case NORTH:
                return new Vector(0, -1);
            case EAST:
                return new Vector(1, 0);
            case SOUTH:
                return new Vector(0, 1);
            case WEST:
                return new Vector(-1, 0);
            default:
                return new Vector();
        }
    }

    public void changeDirection() {
        float orientationTmp = _orientation % 360;
        if ((orientationTmp < 45 && orientationTmp >= 0) || (orientationTmp < 360 && orientationTmp > 315)) {
            _direction = Cap.EAST;
        } else if (orientationTmp < 135 && orientationTmp >= 45) {
            _direction = Cap.NORTH;
        } else if (orientationTmp < 225 && orientationTmp >= 135 ) {
            _direction = Cap.WEST;
        } else if (orientationTmp < 360 && orientationTmp >= 225) {
            _direction = Cap.SOUTH;
        }
    }

    public void changeOrientation(Cap cap) {
        switch (cap) {
            case EAST:
                _orientation = (_orientation + 5) % 360;
                break;
            case WEST:
                _orientation = (_orientation - 5) % 360;
                break;
            default:
                break;
        }
        changeDirection();
        changePosition();
    }

    public void windAction() {

    }

    public Vector changePosition() {

        _position._x += Math.cos(Math.PI/180 * _orientation) * _windSpeed;
        _position._y += Math.sin(Math.PI/180 * _orientation) * _windSpeed;
/*        System.out.println("Boat position: " + _position._x + " " + _position._y);
        System.out.println("Boat orientation: " + _orientation);*/
        return _position;
    }
}