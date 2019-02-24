package com.example.spring_mvc_demo.controller;

import com.example.spring_mvc_demo.model.ElectronicComponent;
import com.example.spring_mvc_demo.service.core.ElectronicComponentRepository;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Api(
        tags = {"Component catalog"}
)
@Controller
@RequestMapping("/catalog")
public class ComponentCatalogController {

    private final ElectronicComponentRepository componentRepository;

        //final List<ElectronicComponent> components = componentRepository.findAll();
    public ComponentCatalogController(ElectronicComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
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
}
