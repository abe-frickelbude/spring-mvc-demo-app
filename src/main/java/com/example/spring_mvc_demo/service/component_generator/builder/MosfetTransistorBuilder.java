package com.example.spring_mvc_demo.service.component_generator.builder;

import com.example.spring_mvc_demo.model.MosfetTransistor;
import com.example.spring_mvc_demo.service.component_generator.BasicValueFiller;
import com.example.spring_mvc_demo.service.component_generator.ComponentBuilder;
import com.example.spring_mvc_demo.util.RandomValueUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MosfetTransistorBuilder implements ComponentBuilder<MosfetTransistor> {

    private static final float MIN_IDS_MAX = 0.1f; // 100 mA
    private static final float MAX_IDS_MAX = 10.0f; // 10 A
    private static final float MIN_RDS_ON = 0.001f; // 1 mOhm, not unrealistic for modern low-voltage MOSFETs
    private static final float MAX_RDS_ON = 25.0f; // 25 Ohm

    private final BasicValueFiller basicValueFiller;

    @Autowired
    public MosfetTransistorBuilder(BasicValueFiller basicValueFiller) {
        this.basicValueFiller = basicValueFiller;
    }

    @Override
    public MosfetTransistor buildComponent() {
        MosfetTransistor transistor = new MosfetTransistor();
        basicValueFiller.fill(transistor);
        transistor.setPolarity(RandomValueUtils.pickRandomValue(MosfetTransistor.Polarity.values()));
        transistor.setIdsMax(RandomUtils.nextFloat(MIN_IDS_MAX, MAX_IDS_MAX));
        transistor.setRdsON(RandomUtils.nextFloat(MIN_RDS_ON, MAX_RDS_ON));
        return transistor;
    }
}
