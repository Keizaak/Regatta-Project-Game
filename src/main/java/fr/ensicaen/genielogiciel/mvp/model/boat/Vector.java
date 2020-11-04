package fr.ensicaen.genielogiciel.mvp.model.boat;

import java.util.Objects;

public class Vector {
    public float _x;
    public float _y;

    public Vector() {
        _x = 0;
        _y = 0;
    }

    public Vector(float x, float y) {
        _x = x;
        _y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return Float.compare(vector._x, _x) == 0 &&
                Float.compare(vector._y, _y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_x, _y);
    }
}
