package com.example.spring_mvc_demo.service.component_generator;

import com.example.spring_mvc_demo.model.ElectronicComponent;

/**
 * Base interface for various random component data builders.
 * 
 * Contract: a component builder <strong>must</strong> fill in the manufacturer and description fields, and should
 * then assign component-specific values
 * 
 * The simple way to fulfill the above requirement is to use the {@linkplain BasicValueFiller} service to fill out the
 * essential fields of a newly generated component at the beginning of buildComponent().
 * 
 * @author Ibragim Kuliev
 *
 * @param <T>
 *        specific component type, e.g. Resistor
 */
public interface ComponentBuilder<T extends ElectronicComponent> {

    /**
     * 
     * @return New component instance
     */
    T buildComponent();
}
