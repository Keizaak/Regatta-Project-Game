package fr.ensicaen.genielogiciel.mvp.model.course;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private List<Buoy> _buoys;

    public Path() {
        _buoys = new ArrayList<>();
    }

    public void loadPath() {
        _buoys.clear();
        new PathFileReader().loadBuoys(_buoys);
    }

    public void cleanPath() {
        _buoys.clear();
    }
}
