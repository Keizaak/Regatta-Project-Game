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

    void changeOrientation(Cap cap) {
        _orientation = getDirection(cap);
    }

    public Vector changePosition(Vector direction, Vector position){
        float pos_x;
        float pos_y;

        pos_x = position._x + (position._x * direction._x);
        pos_y = position._y + (position._y * direction._y);
        _position = new Vector(pos_x, pos_y);
        System.out.println("Position bato " + pos_x + " " + pos_y);
        return _position;
    }
}
