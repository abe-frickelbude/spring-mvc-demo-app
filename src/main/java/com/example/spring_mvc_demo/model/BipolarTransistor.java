package com.example.spring_mvc_demo.model;

public class BipolarTransistor extends ElectronicComponent {

    public static final String TYPE = "bjt";

    public static enum Polarity {
        PNP, NPN
    }

    private int VceMax;
    private float IcMax;
    private int hFE;
    private Polarity polarity;

    public BipolarTransistor() {
        super(TYPE);
    }

    public int getVceMax() {
        return VceMax;
    }

    public void setVceMax(final int vceMax) {
        VceMax = vceMax;
    }

    public float getIcMax() {
        return IcMax;
    }

    public void setIcMax(final float icMax) {
        IcMax = icMax;
    }

    public int gethFE() {
        return hFE;
    }

    public void sethFE(final int hFE) {
        this.hFE = hFE;
    }

    public Polarity getPolarity() {
        return polarity;
    }

    public void setPolarity(final Polarity polarity) {
        this.polarity = polarity;
    }
}
