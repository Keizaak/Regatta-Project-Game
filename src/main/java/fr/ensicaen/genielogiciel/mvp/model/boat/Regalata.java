package fr.ensicaen.genielogiciel.mvp.model.boat;

import java.util.Vector;

public class Regalata extends Boat {

    Vector getDirection(Cap cap) {
        switch (cap) {
            case NORTH:
                return new Vector(0, 1);
            case EAST:
                return new Vector(1, 0);
            case SOUTH:
                return new Vector(0, -1);
            case WEST:
                return new Vector(-1, 0);
            default:
                return new Vector();
        }
    }

    void changeDirection(Vector v){
        /* position * direction */
    }


}
