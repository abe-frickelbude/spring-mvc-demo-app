package com.example.spring_mvc_demo.model;

public class MosfetTransistor extends ElectronicComponent {

    public static final String TYPE = "mosfet";

    public static enum Polarity {
        N_CHANNEL, P_CHANNEL
    }

    private float IdsMax;
    private float RdsON;
    private Polarity polarity;

    public MosfetTransistor() {
        super(TYPE);
    }

    public float getIdsMax() {
        return IdsMax;
    }

    public void setIdsMax(final float idsMax) {
        IdsMax = idsMax;
    }

    public float getRdsON() {
        return RdsON;
    }

    public void setRdsON(final float rdsON) {
        RdsON = rdsON;
    }

    public Polarity getPolarity() {
        return polarity;
    }

    public void setPolarity(final Polarity polarity) {
        this.polarity = polarity;
    }
}
