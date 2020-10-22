package fr.ensicaen.genielogiciel.mvp.model.course;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class CourseTest {

    @Test
    public void test_load_weather() {
        Course course = new Course();

        try {
            course.load_weather();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}