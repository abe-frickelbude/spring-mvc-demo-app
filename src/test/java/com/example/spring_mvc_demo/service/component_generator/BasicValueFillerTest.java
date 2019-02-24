package com.example.spring_mvc_demo.service.component_generator;

import com.example.spring_mvc_demo.model.Resistor;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for {@linkplain BasicValueFiller}
 *
 * In this case, the BasicValueFiller component under test is very simple as it doesn't have any dependencies,
 * so the test is quite straightforward as well.
 *
 */
public class BasicValueFillerTest {

    @Test
    public void testFill() {

        final BasicValueFiller filler = new BasicValueFiller();
        final Resistor resistor = new Resistor();

        filler.fill(resistor);
        assertNotNull(resistor.getManufacturer());
        assertNotNull(resistor.getDescription());
    }
}