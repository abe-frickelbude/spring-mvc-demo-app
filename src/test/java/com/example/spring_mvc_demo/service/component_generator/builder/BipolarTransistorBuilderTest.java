package com.example.spring_mvc_demo.service.component_generator.builder;

import com.example.spring_mvc_demo.model.BipolarTransistor;
import com.example.spring_mvc_demo.model.ElectronicComponent;
import com.example.spring_mvc_demo.model.Manufacturer;
import com.example.spring_mvc_demo.service.component_generator.BasicValueFiller;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

/**
 * Unit test for {@linkplain BipolarTransistorBuilder}
 * <p>
 * This is an example of a unit test for a component that inspects the component in isolation and under
 * controlled conditions. For this purpose, we use Mockito to substitute our component's dependencies
 * with mocks/stubs. This allows us to explicitly specify behavior of said dependencies, e.g. return values.
 * <p>
 * The test below includes BDD (behavior-driven development) using the typical "given/when/then" terminology.
 * There are also some basic examples of using JUnit / Hamcrest assertions and matchers.
 * <p>
 * Note how initialization of mocks and their behavior produces overhead "on top of" the actual test case(s).
 * For this reason, unit-testing of complicated component implementations can quickly get out of hand - in such
 * situations it may be more reasonable to use a real application context and write an integration test instead.
 */
@RunWith(MockitoJUnitRunner.class)
public class BipolarTransistorBuilderTest {

    // will be automatically mocked by Mockito
    @Mock
    private BasicValueFiller basicValueFiller;

    // The MockitoJUnitRunner will attempt to intelligently inject members tagged with @Mock/@Spy above
    @InjectMocks
    private BipolarTransistorBuilder subject;

    // @Before is executed before every test
    @Before
    public void setUp() {
        // Hint: we can manually mock()/stub() things here instead of an annotation-driven approach
        // Hint: we can do mock/stub behavior specification here if it is shared by multiple tests
    }

    @Test
    public void testBuildComponent() {

        // could also alternatively use "when" here
        given(basicValueFiller.fill(any(ElectronicComponent.class))).willAnswer((invocation) -> {
            ElectronicComponent component = invocation.getArgument(0);
            component.setManufacturer(Manufacturer.TI);
            component.setDescription("description");
            return null;
        });

        // "when"
        final BipolarTransistor transistor = subject.buildComponent();

        // verify expectations - the builder should have called the fill() in BasicValueFiller exactly one time
        then(basicValueFiller).should(times(1)).fill(transistor);

        // and attributes
        assertThat(transistor, notNullValue());

        // Hint: we can also use JUnit's assertNotNull() as an alternative to Hamcrest matchers
        // however, the Hamcrest library is generally more flexible and provides many specialized assertions
        // not available in JUnit
        assertNotNull(transistor.getCreatedAt());

        assertThat(transistor.getManufacturer(), is(Manufacturer.TI));
        assertThat(transistor.getDescription(), is("description"));

        assertThat(transistor.getPolarity(), notNullValue());
        assertTrue(transistor.gethFE() > 0);
        assertTrue(transistor.getIcMax() > 0);
        assertTrue(transistor.getVceMax() > 0);
    }
}