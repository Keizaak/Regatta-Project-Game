package fr.ensicaen.genielogiciel.mvp.model.course;

public class Course {
    private final Path _path;

    public Course() {
        _path = new Path();
        _path.loadPath();
    }

    public Path getPath() {
        return _path;
    }
}
