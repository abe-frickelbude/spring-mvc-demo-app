package com.example.spring_mvc_demo.service.component_generator.builder;

import com.example.spring_mvc_demo.model.Inductor;
import com.example.spring_mvc_demo.service.component_generator.BasicValueFiller;
import com.example.spring_mvc_demo.service.component_generator.ComponentBuilder;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InductorBuilder implements ComponentBuilder<Inductor> {

    private static final float MIN_VALUE = 1e-7f; // 0.1 uH
    private static final float MAX_VALUE = 1.0f; // 1 H

    private final BasicValueFiller basicValueFiller;

    @Autowired
    public InductorBuilder(BasicValueFiller basicValueFiller) {
        this.basicValueFiller = basicValueFiller;
    }

    @Override
    public Inductor buildComponent() {
        Inductor inductor = new Inductor();
        basicValueFiller.fill(inductor);
        inductor.setTolerance(BuilderUtils.pickRandomTolerance());
        inductor.setValue(RandomUtils.nextFloat(MIN_VALUE, MAX_VALUE));
        return inductor;
    }
}
