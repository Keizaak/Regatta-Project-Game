package fr.ensicaen.genielogiciel.mvp.model.course;

import fr.ensicaen.genielogiciel.mvp.presenter.command.FileReader;

public class Path implements FileReader {
    private Buoy _buoy;
    private Path() {
        _buoy = new Buoy();
    }

}
