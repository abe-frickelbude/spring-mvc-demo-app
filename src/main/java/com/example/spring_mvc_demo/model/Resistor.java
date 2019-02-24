package com.example.spring_mvc_demo.model;

import javax.validation.constraints.Min;

/**
 * Resistors. Resistance value unit is [Ohm], tolerance is specified in %.
 * 
 * @author Ibragim Kuliev
 *
 */
public class Resistor extends ElectronicComponent {

    public static final String TYPE = "resistor";

    private float value;

    @Min(1)
    private int tolerance;

    public Resistor() {
        super(TYPE);
    }

    public float getValue() {
        return value;
    }

    public void setValue(final float value) {
        this.value = value;
    }

    public int getTolerance() {
        return tolerance;
    }

    public void setTolerance(final int tolerance) {
        this.tolerance = tolerance;
    }
}
