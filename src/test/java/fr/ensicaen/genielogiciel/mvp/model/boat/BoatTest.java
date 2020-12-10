package fr.ensicaen.genielogiciel.mvp.model.boat;

import fr.ensicaen.genielogiciel.mvp.model.Vector;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

public class BoatTest {

    @Test
    public void getDirectionTest() {
        Regalata regalata = new Regalata(Cap.SOUTH, 0.80f);

        Cap testCap = Cap.EAST;
        assertEquals(new Vector(1, 0), regalata.getDirection(testCap));

        testCap = Cap.WEST;
        assertEquals(new Vector(-1, 0), regalata.getDirection(testCap));
    }

    /* TODO: mettre les méthodes getter attributs à tester en package visibility */
}
