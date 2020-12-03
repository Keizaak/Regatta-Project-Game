package fr.ensicaen.genielogiciel.mvp.model.boat;

import java.util.Objects;

public class Vector {
    public float x;
    public float y;

    public Vector() {
        x = 0;
        y = 0;
    }

    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vector vector = (Vector) o;
        return Float.compare(vector.x, x) == 0 &&
                Float.compare(vector.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
