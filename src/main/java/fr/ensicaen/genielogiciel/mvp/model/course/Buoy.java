package fr.ensicaen.genielogiciel.mvp.model.course;

import fr.ensicaen.genielogiciel.mvp.model.Vector;

import java.util.Observable;
import java.util.Observer;

public class Buoy implements Observer {
    private final Vector _position;
    private final Vector _limit;

    public Buoy(Vector position, Vector limit) {
        _position = position;
        _limit = limit;
    }

    public Vector getPosition() {
        return _position;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
