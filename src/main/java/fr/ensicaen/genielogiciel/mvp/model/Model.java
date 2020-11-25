package fr.ensicaen.genielogiciel.mvp.model;

import fr.ensicaen.genielogiciel.mvp.model.boat.Boat;
import fr.ensicaen.genielogiciel.mvp.model.boat.Regalata;
import fr.ensicaen.genielogiciel.mvp.model.boat.Vector;

public class Model {
    private Regalata _regalata;
    private String _nickname;



    public Model() {
        _regalata = new Regalata();
    }

    public String getNickname() {
        return _nickname;
    }

    public void setNickname(String nickname) {
        _nickname = nickname;
    }

    public void turnBoatLeft() {
        Vector v = _regalata.changePosition(_regalata.getDirection(Boat.Cap.WEST), _regalata.getPosition());
        /*TODO
        Do something with v ?
         */
    }

    public void turnBoatRight() {
        Vector v = _regalata.changePosition(_regalata.getDirection(Boat.Cap.EAST), _regalata.getPosition());
        /*TODO
        Do something with v ?
         */
    }
}
