package fr.ensicaen.genielogiciel.mvp.model.course;

public class Course {
    private Path _path;

    public Course() {
        _path = new Path();
        _path.loadPath();
    }
}
