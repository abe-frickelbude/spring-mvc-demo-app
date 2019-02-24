package com.example.spring_mvc_demo.service.component_generator;

import com.example.spring_mvc_demo.model.ElectronicComponent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertNotNull;

/**
 * Integration test for {@linkplain RandomComponentFactory}.
 *
 * This is an example of a "component" (alternative name: integration) test that uses an actual application
 * context in contrast to a unit test. In cases like this, the overhead of mocking/stubbing dependencies of a
 * component under test becomes unreasonable, and so it may be more feasible to actually initialize a [partial] app
 * context and let the container perform DI and other initialization.
 *
 * Assuming the components' dependencies have themselves been properly unit-tested in isolation and can be considered
 * "proven working", this kind of component test is now a reasonable compromise, even though it is not "pure"
 * w.r.t to outside influence by the application framework(s).
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class RandomComponentFactoryTest {

    private static final Logger log = LoggerFactory.getLogger(RandomComponentFactoryTest.class);

    private static final int NUM_ELEMENTS = 100000;

    @Autowired
    private RandomComponentFactory componentFactory;

    @Test
    public void testMakeComponent() {
        for (int i = 0; i < 10; i++) {
            ElectronicComponent component = componentFactory.makeComponent();
            assertNotNull(component);
            assertNotNull(component.getId());
            assertNotNull(component.getDescription());
            assertNotNull(component.getCreatedAt());
            assertNotNull(component.getManufacturer());
            log.info(component.toString());
        }
    }

    @Test
    public void testComponentDistribution() {

        // initialize counters
        Map<Class<?>, AtomicInteger> counters = new HashMap<>();
        for (Class<?> componentClass : componentFactory.getComponentClasses()) {
            counters.put(componentClass, new AtomicInteger());
        }

        for (int i = 0; i < NUM_ELEMENTS; i++) {
            ElectronicComponent component = componentFactory.makeComponent();
            counters.get(component.getClass()).getAndIncrement();
        }

        log.info("Generated component distribution:");
        for (Class<?> componentClass : counters.keySet()) {
            log.info("{} : {}", componentClass.getSimpleName(), counters.get(componentClass));
        }
        log.info("\n");
    }
}
