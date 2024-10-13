package org.example.pizzeriadiegovega;

public class Pizza {
    private String nombre;
    private String desc;
    private double precio;

    public Pizza(String nombre, String desc, double precio) {
        this.nombre = nombre;
        this.desc = desc;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDesc() {
        return desc;
    }

    public double getPrecio() {
        return precio;
    }
}
