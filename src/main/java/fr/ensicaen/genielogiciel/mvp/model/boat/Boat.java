package fr.ensicaen.genielogiciel.mvp.model.boat;

public abstract class Boat {

    enum Cap {
        EAST,
        WEST,
        NORTH,
        SOUTH
    }

    protected Vector _position;
    protected Vector _orientation;
    protected Cap _windOrientation;
    protected float _windSpeed;

    abstract Vector getDirection(Cap cap);
    abstract void changeOrientation(Cap cap);
    abstract Vector changePosition(Vector direction, Vector position);
}