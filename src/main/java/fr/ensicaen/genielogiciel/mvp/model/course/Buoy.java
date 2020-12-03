package fr.ensicaen.genielogiciel.mvp.model.course;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

public class Buoy implements Observer {
    private final Vector _position;

    public Buoy(Vector position) {
        _position = position;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
