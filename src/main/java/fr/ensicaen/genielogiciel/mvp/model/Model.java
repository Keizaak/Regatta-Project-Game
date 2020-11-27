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

    public Vector getRegalataPosition() {
        return _regalataPosition;
    }

    public void turnBoatLeft() {
        Vector v = _regalata.changePosition(_regalata.getDirection(Boat.Cap.WEST), _regalata.getPosition());
        _regalataPosition = v;
        /*TODO
        Do something with v ?
         */
    }

    public void turnBoatRight() {
        Vector v = _regalata.changePosition(_regalata.getDirection(Boat.Cap.EAST), _regalata.getPosition());
        _regalataPosition = v;
        /*TODO
        Do something with v ?
         */
    }
}
