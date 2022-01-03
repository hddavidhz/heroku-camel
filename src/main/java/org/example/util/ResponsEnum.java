package org.example.util;

public enum ResponsEnum {

    API_REST_CLIENTE ("http://localhost:8011/clientes/"),
    VELOCITY_TRANSFORM ( "velocity:transformData.vm"),
    VELOCITY_TRANSFORM_2 ( "velocity:transformData2.vm");

    private String description;

    ResponsEnum (String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
