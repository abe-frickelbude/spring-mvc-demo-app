package com.example.spring_mvc_demo.service.core;

import com.example.spring_mvc_demo.model.ElectronicComponent;
import com.example.spring_mvc_demo.service.component_generator.RandomComponentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * An in-memory-only implementation of {@link ElectronicComponentRepository}. It automatically assigns unique
 * sequential IDs to components
 */
@Service
public class InMemoryRepository implements ElectronicComponentRepository {

    private static final Logger log = LoggerFactory.getLogger(InMemoryRepository.class);

    private final RandomComponentFactory componentFactory;

    // data
    private final Integer numItems;
    private final Map<Long, ElectronicComponent> componentMap;
    private final List<ElectronicComponent> componentList;

    private final AtomicLong idSequence;

    // Hint: @Autowired can be often be omitted, provided the constructor signature is unambiguous
    @Autowired
    public InMemoryRepository(final RandomComponentFactory componentFactory,
                              @Value("${component-catalog.num-random-items}") final Integer numItems) {

        this.componentFactory = componentFactory;
        this.numItems = numItems;

        componentMap = new LinkedHashMap<>(numItems);
        componentList = new ArrayList<>(numItems);
        idSequence = new AtomicLong(0);
    }

    // @PostConstruct called after all DI / value injection is completed
    @PostConstruct
    public void init() {
        populateComponentList();
    }

    private void populateComponentList() {
        for (int i = 0; i < numItems; i++) {
            final ElectronicComponent component = componentFactory.makeComponent();

            // set ID
            component.setId(idSequence.getAndIncrement());

            componentList.add(component);
            componentMap.put(component.getId(), component);
        }
        log.info("Populated repository with {} randomly generated items!", numItems);
    }

    @Override
    public ElectronicComponent find(final Long id) {
        return componentMap.get(id);
    }

    @Override
    public Page<ElectronicComponent> find(final Pageable pageable) {
        return null;
    }

    @Override
    public List<ElectronicComponent> findAll() {
        return Collections.unmodifiableList(componentList);
    }

    @Override
    public ElectronicComponent save(final ElectronicComponent component) {

        // assign an ID if messing
        if (component.getId() == null) {
            component.setId(idSequence.getAndIncrement());
        }

        final ElectronicComponent previous = componentMap.get(component.getId());
        if (previous == null) {
            // add if not already contained in the list
            componentList.add(component);
        } else {
            // update
            componentList.remove(previous);
            componentList.add(component);
        }
        componentMap.put(component.getId(), component);
        return component;
    }

    @Override
    public void delete(final Long id) {
        final ElectronicComponent component = componentMap.get(id);
        if (component != null) {
            componentList.remove(component);
            componentMap.remove(id);
        }
    }
}
