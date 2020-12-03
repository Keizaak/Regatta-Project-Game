package fr.ensicaen.genielogiciel.mvp.model.course;

import java.util.Observable;
import java.util.Observer;

public class Buoy implements Observer {
    private final int _x;
    private final int _y;

    public Buoy(int x, int y) {
        _x = x;
        _y = y;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
