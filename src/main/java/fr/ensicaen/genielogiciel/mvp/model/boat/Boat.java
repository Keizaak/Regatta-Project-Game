package fr.ensicaen.genielogiciel.mvp.model.boat;

import fr.ensicaen.genielogiciel.mvp.model.Vector;

import java.util.Observable;

public abstract class Boat extends Observable {
    private Vector _position;
    private Cap _direction;
    private float _orientation;
    private Cap _windDirection;
    private float _windSpeed;
    private boolean _isFaster;

    /* TODO: get wind direction and speed from JSON (it's fixed for the moment) */
    protected Boat() {
        _position = new Vector();
        _direction = Cap.NORTH;
        _orientation = 270;
        _windDirection = Cap.SOUTH;
        _windSpeed = 0.80f;
        _isFaster = false;
    }

    protected Boat(Cap cap, float speed) {
        super();
        _windDirection = cap;
        _windSpeed = speed;
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

    /* TODO: optimize... */
    public void windAction() {
        if (_windDirection == _direction) {
            if (!_isFaster) {
                _windSpeed *= 1.5;
                _isFaster = true;
            }
        } else if ((_windDirection == Cap.NORTH && _direction == Cap.SOUTH) || (_windDirection == Cap.SOUTH && _direction == Cap.NORTH)
                || (_windDirection == Cap.WEST && _direction == Cap.EAST) || (_windDirection == Cap.EAST && _direction == Cap.WEST))
        {
            if (_isFaster) {
                _windSpeed /= 1.5;
                _isFaster = false;
            }
        } else {
            switch (_direction) {
                case NORTH:
                    if (_windDirection == Cap.EAST) {
                        addValueToOrientation(0.2f);
                    } else if (_windDirection == Cap.WEST) {
                        addValueToOrientation(-0.2f);
                    }
                    break;
                case SOUTH:
                    if (_windDirection == Cap.EAST) {
                        addValueToOrientation(-0.2f);
                    } else if (_windDirection == Cap.WEST) {
                        addValueToOrientation(0.2f);

                    }
                    break;
                case EAST:
                    if (_windDirection == Cap.SOUTH) {
                        addValueToOrientation(0.2f);
                    } else if (_windDirection == Cap.NORTH){
                        addValueToOrientation(-0.2f);
                    }
                    break;
                case WEST:
                    if (_windDirection == Cap.SOUTH) {
                        addValueToOrientation(-0.2f);
                    } else if (_windDirection == Cap.NORTH) {
                        addValueToOrientation(0.2f);
                    }
                    break;
            }
        }
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
        float orientationTmp;

        if (_orientation < 0) {
            orientationTmp = (_orientation + 360) % 360;
        } else {
            orientationTmp = _orientation % 360;
        }

        if ((orientationTmp < 45 && orientationTmp >= 0) || (orientationTmp < 360 && orientationTmp >= 315)) {
            _direction = Cap.EAST;
        } else if (orientationTmp < 135 && orientationTmp >= 45) {
            _direction = Cap.SOUTH;
        } else if (orientationTmp < 225 && orientationTmp >= 135 ) {
            _direction = Cap.WEST;
        } else if (orientationTmp < 315 && orientationTmp >= 225) {
            _direction = Cap.NORTH;
        }
    }

    public void addValueToOrientation(float value) {
        _orientation = (_orientation + value) % 360;
    }

    public void changeOrientation(Cap cap) {
        switch (cap) {
            case EAST:
                addValueToOrientation(5);
                break;
            case WEST:
                addValueToOrientation(-5);
                break;
            default:
                break;
        }

        changeDirection();
        changePosition();
    }

    public void changePosition() {
        _position.x += Math.cos(Math.PI/180 * _orientation) * _windSpeed;
        _position.y += Math.sin(Math.PI/180 * _orientation) * _windSpeed;
    }

    public void setPositionForBuoy(Vector position) {
        _position = position;
        setChanged();
        notifyObservers(position);
    }
}