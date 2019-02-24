package com.example.spring_mvc_demo.service.component_generator;

import com.example.spring_mvc_demo.model.ElectronicComponent;
import com.example.spring_mvc_demo.util.RandomValueUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A factory class that generates various types of {@linkplain ElectronicComponent} with randomly
 * assigned values.
 *
 * @author Ibragim Kuliev
 */
@Service
public class RandomComponentFactory {

    private static Logger log = LoggerFactory.getLogger(RandomComponentFactory.class);

    private final ApplicationContext appContext;

    private Map<Class<?>, ComponentBuilder<?>> builderRegistry;
    private List<Class<?>> componentClasses;

    @Autowired
    public RandomComponentFactory(ApplicationContext appContext) {
        this.appContext = appContext;
    }

    @PostConstruct
    public void init() {
        builderRegistry = new HashMap<>();
        componentClasses = new ArrayList<>();
        for (ComponentBuilder<?> builder : appContext.getBeansOfType(ComponentBuilder.class).values()) {
            addBuilder(builder);
        }
    }

    public List<Class<?>> getComponentClasses() {
        return new ArrayList<>(componentClasses);
    }

    public ElectronicComponent makeComponent() {
        Class<?> componentClass = RandomValueUtils.pickRandomValue(componentClasses);
        ComponentBuilder<?> builder = builderRegistry.get(componentClass);
        ElectronicComponent component = builder.buildComponent();
        return component;
    }

    /**
     * Registers a builder with the factory. The method uses some Spring magic to determine the source data type
     * of a builder.
     *
     * @param builder a subclass of {@linkplain ComponentBuilder} to be registered.
     */
    private void addBuilder(final ComponentBuilder<?> builder) {

        ResolvableType type = ResolvableType.forClass(builder.getClass()).as(ComponentBuilder.class);
        ResolvableType[] generics = type.getGenerics();
        if (generics.length < 1) {
            throw new IllegalArgumentException("Unable to determine source data type for supplied builder instance!");
        }

        Class<?> sourceType = generics[0].resolve();
        builderRegistry.put(sourceType, builder);
        componentClasses.add(sourceType);
        log.info("Registered component builder for {}", sourceType.getSimpleName());
    }
}
