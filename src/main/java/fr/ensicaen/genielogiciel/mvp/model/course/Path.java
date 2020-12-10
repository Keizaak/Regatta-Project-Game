package fr.ensicaen.genielogiciel.mvp.model.course;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Path {
    private final List<Buoy> _buoys;

    public Path() {
        _buoys = new ArrayList<>();
    }

    public void loadPath() {
        _buoys.clear();
        new PathFileReader().loadBuoys(_buoys);
    }
}
