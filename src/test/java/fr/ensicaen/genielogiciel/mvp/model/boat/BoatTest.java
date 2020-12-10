package fr.ensicaen.genielogiciel.mvp.model.boat;

import fr.ensicaen.genielogiciel.mvp.model.Vector;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoatTest {

    @Test
    public void getVectorDirectionTest() {
        Regalata regalata = new Regalata(Cap.SOUTH, 80f);

        Cap testCap = Cap.EAST;
        assertEquals(new Vector(1, 0), regalata.getVectorDirection(testCap));

        testCap = Cap.WEST;
        assertEquals(new Vector(-1, 0), regalata.getVectorDirection(testCap));
    }

    @Test
    public void changeDirectionTest() {
        Regalata regalata = new Regalata(Cap.SOUTH, 80f);

        regalata.setOrientation(320f);
        regalata.changeDirection();
        assertEquals(Cap.EAST, regalata.getDirection());

        regalata.setOrientation(50f);
        regalata.changeDirection();
        assertEquals(Cap.SOUTH, regalata.getDirection());

        regalata.setOrientation(270f);
        regalata.changeDirection();
        assertEquals(Cap.NORTH, regalata.getDirection());

        regalata.setOrientation(150f);
        regalata.changeDirection();
        assertEquals(Cap.WEST, regalata.getDirection());
    }

    @Test
    public void addValueToOrientationTest() {
        Regalata regalata = new Regalata(Cap.SOUTH, 80f);
        regalata.addValueToOrientation(1f);
        assertEquals(271f, regalata.getOrientation(), 0.9);
    }

    @Test
    public void changePositionTest() {
        Regalata regalata = new Regalata(Cap.SOUTH, 80f);

        regalata.changePosition();

        System.out.println(regalata.getPosition().x + " " + regalata.getPosition().y);

        assertEquals(0f, regalata.getPosition().x, 0.000000000001f);
        assertEquals(-1.6f, regalata.getPosition().y, 0.1f);
    }
}
