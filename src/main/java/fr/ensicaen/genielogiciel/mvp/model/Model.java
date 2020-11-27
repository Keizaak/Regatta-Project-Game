package fr.ensicaen.genielogiciel.mvp.model;

import fr.ensicaen.genielogiciel.mvp.model.boat.Boat;
import fr.ensicaen.genielogiciel.mvp.model.boat.Regalata;
import fr.ensicaen.genielogiciel.mvp.model.boat.Vector;

public class Model {
    private Regalata _regalata;
    private String _nickname;
    private Vector _regalataPosition;



    public Model() {
        _regalata = new Regalata();
        _regalataPosition = _regalata.getPosition();
    }

    public String getNickname() {
        return _nickname;
    }

    public void setNickname(String nickname) {
        _nickname = nickname;
    }
    
    public void initPosition(float x, float y) {
        _regalata.setPosition(new Vector(x, y));
    }
    
    public float getOrientation() {
        return _regalata.getOrientation();
    }

    public Vector getRegalataPosition() {
        return _regalataPosition;
    }

    public void turnBoatLeft() {
        _regalata.changeOrientation(Boat.Cap.WEST);
        Vector v = _regalata.getPosition();
        _regalataPosition = v;
        /*TODO
        Do something with v ?
         */
    }

    public void turnBoatRight() {
        _regalata.changeOrientation(Boat.Cap.EAST);
        Vector v = _regalata.getPosition();
        _regalataPosition = v;
        /*TODO
        Do something with v ?
         */
    }
    
    public void movingForward() {
        _regalata.changePosition();
    }
}
