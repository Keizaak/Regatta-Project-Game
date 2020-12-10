package fr.ensicaen.genielogiciel.mvp.model.boat;

import fr.ensicaen.genielogiciel.mvp.model.Vector;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RegalataTest {

    @Test
    public void getDirectionTest() {
        Regalata regalata = new Regalata();

        Boat.Cap testCap = Boat.Cap.EAST;
        assertEquals(new Vector(1, 0), regalata.getDirection(testCap));

        testCap = Boat.Cap.WEST;
        assertEquals(new Vector(-1, 0), regalata.getDirection(testCap));
    }

    /* TODO: Test changeOrientation */

    /*
    @Test
    public void changePositionTest() {
        Regalata regalata = new Regalata();

        Vector directionTest = new Vector(-1, 0);
        Vector positionTest = new Vector(1, 1);

        assertEquals(new Vector(0, 1), regalata.changePosition(directionTest, positionTest));
    }
    */
}
