package com.example.spring_mvc_demo.model;

/**
 * Standard PN junction or Schottky diodes.
 * 
 * @author Ibragim Kuliev
 *
 */
public class Diode extends ElectronicComponent {

    public static final String TYPE = "diode";

    private float Vf; // forward voltage, in V
    private int VrevMax; // max. allowed reverse voltage, in V
    private float IfMax; // max. forward current, in A

    public Diode() {
        super(TYPE);
    }

    public float getVf() {
        return Vf;
    }

    public void setVf(final float vf) {
        Vf = vf;
    }

    public int getVrevMax() {
        return VrevMax;
    }

    public void setVrevMax(final int vrevMax) {
        VrevMax = vrevMax;
    }

    public float getIfMax() {
        return IfMax;
    }

    public void setIfMax(final float ifMax) {
        IfMax = ifMax;
    }
}
