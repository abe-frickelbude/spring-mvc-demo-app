package com.example.spring_mvc_demo.controller;

import com.example.spring_mvc_demo.model.ElectronicComponent;
import com.example.spring_mvc_demo.service.core.ElectronicComponentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Api(
        tags = {"Component catalog"}
)
@Controller
@RequestMapping("/catalog")
public class ComponentCatalogController {

    private final ElectronicComponentRepository componentRepository;
    private final ObjectMapper objectMapper;

        //final List<ElectronicComponent> components = componentRepository.findAll();
    public ComponentCatalogController(final ElectronicComponentRepository componentRepository,
                                      final ObjectMapper objectMapper) {
        this.componentRepository = componentRepository;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/list")
    public String getComponentList(final Model model,
                                   @RequestParam(value = "page", defaultValue = "1") Integer currentPage,
                                   @RequestParam(value = "size", defaultValue = "10") Integer pageSize) {

        final Page<ElectronicComponent> componentPage
                = componentRepository.find(PageRequest.of(currentPage -1, pageSize));

        model.addAttribute("componentPage", componentPage);

        // add page numbers for links
        int totalPages = componentPage.getTotalPages();
        if(totalPages > 0) {

            // baeh... being lazy resulted in a syntactic mess here :-(
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        // this is the name of the associated Thymeleaf template
        return "component_list";
    }

    @GetMapping("/detail/{id}")
    public String getDetailPage(final Model model, @PathVariable("id") final Long id) {

        final ElectronicComponent component = componentRepository.find(id);
        if(component != null) {
            // again, i'm a bit lazy here -> just dumped component details into a map for easier access in the
            // Thymeleaf template
            Map<String, Object> data = objectMapper.convertValue(component, Map.class);
            model.addAttribute("componentData", data);
        } else {
            return "404";
        }

        return "component_detail_page";
    }
}
