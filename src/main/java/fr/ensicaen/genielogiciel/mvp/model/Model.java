package fr.ensicaen.genielogiciel.mvp.model;

import fr.ensicaen.genielogiciel.mvp.model.boat.Cap;
import fr.ensicaen.genielogiciel.mvp.model.boat.Regalata;
import fr.ensicaen.genielogiciel.mvp.model.course.Path;

public class Model {
    private Path _path;
    private Regalata _regalata;
    private String _nickname;
    private Vector _regalataPosition;

    public Model() {
        _regalata = new Regalata();
        _regalataPosition = _regalata.getPosition();

        _path = new Path();
    }

    public String getNickname() {
        return _nickname;
    }

    public void setNickname(String nickname) {
        _nickname = nickname;
    }
    
    public void initPosition(float x, float y) {
        _regalata.setPosition(new Vector(x, y));
        _regalataPosition = _regalata.getPosition();
    }
    
    public float getOrientation() {
        return _regalata.getOrientation();
    }

    public Vector getRegalataPosition() {
        return _regalataPosition;
    }

    public void turnBoatLeft() {
        _regalata.changeOrientation(Cap.WEST);
        Vector v = _regalata.getPosition();
        _regalataPosition = v;
        /* TODO: Do something with v ? */
    }

    public void turnBoatRight() {
        _regalata.changeOrientation(Cap.EAST);
        Vector v = _regalata.getPosition();
        _regalataPosition = v;
        /* TODO: Do something with v ? */
    }
    
    public void movingForward() {
        _regalata.windAction();
        _regalata.changePosition();

    }
}
