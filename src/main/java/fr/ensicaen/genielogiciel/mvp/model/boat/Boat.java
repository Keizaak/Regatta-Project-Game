package fr.ensicaen.genielogiciel.mvp.model.boat;

import java.util.Vector;

public abstract class Boat {

    enum Cap {
        NORTH,
        EAST,
        SOUTH,
        WEST
    }
    protected Vector _position;

    /* TODO: abstract */
    Vector getDirection(Cap cap) {
        return new Vector();
    }

    /* TODO: abstract */
    void changeDirection(Vector v) {

    }
}
