package fr.ensicaen.genielogiciel.mvp.model.boat;

/* TODO: change in crew and sail */
public class BoatDecorator extends Boat {
    Boat _boat;


    Vector getDirection(Cap cap) {
        return new Vector();
    }

    void changeOrientation(Cap cap) { }

    Vector changePosition(Vector v, Vector position) {
        return new Vector();
    }
}
