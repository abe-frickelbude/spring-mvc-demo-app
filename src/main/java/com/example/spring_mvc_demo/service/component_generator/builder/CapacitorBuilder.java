package com.example.spring_mvc_demo.service.component_generator.builder;

import com.example.spring_mvc_demo.model.Capacitor;
import com.example.spring_mvc_demo.service.component_generator.BasicValueFiller;
import com.example.spring_mvc_demo.service.component_generator.ComponentBuilder;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CapacitorBuilder implements ComponentBuilder<Capacitor> {

    private static final float MIN_VALUE = 1e-12f; // 1pF
    private static final float MAX_VALUE = 1e-3f; // 1000 uF

    @Autowired
    private BasicValueFiller basicValueFiller;

    @Override
    public Capacitor buildComponent() {
        Capacitor capacitor = new Capacitor();
        basicValueFiller.fill(capacitor);
        capacitor.setTolerance(BuilderUtils.pickRandomTolerance());
        capacitor.setValue(RandomUtils.nextFloat(MIN_VALUE, MAX_VALUE));
        return capacitor;
    }
}
