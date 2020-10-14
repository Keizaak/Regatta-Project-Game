package fr.ensicaen.genielogiciel.mvp.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ModelTest {
    @Test
    public void should_get_nickname_when_set_nickname() {
        Model loginModel = new Model();
        final String testString = "Toto";
        loginModel.setNickname(testString);
        assertEquals(testString, loginModel.getNickname());
    }
}