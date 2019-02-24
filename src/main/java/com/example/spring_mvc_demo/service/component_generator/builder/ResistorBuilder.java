package com.example.spring_mvc_demo.service.component_generator.builder;

import com.example.spring_mvc_demo.model.Resistor;
import com.example.spring_mvc_demo.service.component_generator.BasicValueFiller;
import com.example.spring_mvc_demo.service.component_generator.ComponentBuilder;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResistorBuilder implements ComponentBuilder<Resistor> {

    private static final float MIN_VALUE = 0.05f; // 5 mOhm
    private static final float MAX_VALUE = 1.0e7f; // 10 MOhm

    private final BasicValueFiller basicValueFiller;

    @Autowired
    public ResistorBuilder(BasicValueFiller basicValueFiller) {
        this.basicValueFiller = basicValueFiller;
    }

    @Override
    public Resistor buildComponent() {
        Resistor resistor = new Resistor();
        basicValueFiller.fill(resistor);
        resistor.setTolerance(BuilderUtils.pickRandomTolerance());
        resistor.setValue(RandomUtils.nextFloat(MIN_VALUE, MAX_VALUE));
        return resistor;
    }
}
