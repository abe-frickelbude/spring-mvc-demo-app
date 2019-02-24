package com.example.spring_mvc_demo.controller;

import com.example.spring_mvc_demo.model.ElectronicComponent;
import com.example.spring_mvc_demo.service.core.ElectronicComponentRepository;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @RestController flags a controller for additional consideration by Spring MVC, e.g. request handling using
 * message converters (instead of ModelAndView rendering mechanism)
 */
@Api(
        tags = {"Component catalog Rest API"}
)
@RestController
@RequestMapping("/api/component")
public class ComponentController {

    private final ElectronicComponentRepository componentRepository;

    public ComponentController(ElectronicComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    /**
     * A basic example of a request handler method.
     *
     * <p>
     * - @PathVariable extracts and maps path fragments to parameters
     * - ResponseEntity fluent API can be used to provide custom responses, e.g. NOT_FOUND
     * <p>
     * <p>
     * - Observe how it is not necessary to "render" the response data to JSON - since we annotated
     * the controller with @RestController, the data return from the method signals a hand-off to the
     * Spring MVC "Rest" controller pathway and will be automatically serialized using the
     * currently configured ObjectMapper.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ElectronicComponent> find(@PathVariable("id") final Long id) {

        final ElectronicComponent component = componentRepository.find(id);
        if (component != null) {
            return ResponseEntity.ok(component);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ElectronicComponent save(@Valid @RequestBody final ElectronicComponent component) {
        return componentRepository.save(component);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") final Long id) {

        if (componentRepository.find(id) != null) {
            componentRepository.delete(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
