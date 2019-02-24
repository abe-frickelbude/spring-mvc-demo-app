package com.example.spring_mvc_demo.service.component_generator;

import com.example.spring_mvc_demo.model.ElectronicComponent;
import com.example.spring_mvc_demo.model.Manufacturer;
import com.example.spring_mvc_demo.util.RandomValueUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Ibragim Kuliev
 *
 */
@Service
public class BasicValueFiller {

    public ElectronicComponent fill(final ElectronicComponent component) {
        component.setDescription(makeRandomDescription(component.getClass()));
        component.setManufacturer(pickRandomManufacturer());
        return component;
    }

    private Manufacturer pickRandomManufacturer() {
        return RandomValueUtils.pickRandomValue(Manufacturer.values());
    }

    private <T> String makeRandomDescription(final Class<T> componentClass) {
        return componentClass.getSimpleName() + "-" + RandomStringUtils.randomAlphanumeric(8);
    }
}
