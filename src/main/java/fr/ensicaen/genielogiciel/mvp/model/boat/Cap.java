package fr.ensicaen.genielogiciel.mvp.model.boat;

public enum Cap {
    EAST("EAST"),
    WEST("WEST"),
    NORTH("NORTH"),
    SOUTH("SOUTH");

    private String value;

    Cap(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
