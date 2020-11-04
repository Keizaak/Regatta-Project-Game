package fr.ensicaen.genielogiciel.mvp.model.course;

import org.junit.Test;

import java.io.IOException;

public class CourseTest {

    @Test
    public void test_load_weather() {
        Course course = new Course();

        try {
            course.load_weather_info("49.283", "-0.25");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}