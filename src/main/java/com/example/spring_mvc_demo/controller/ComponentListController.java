package com.example.spring_mvc_demo.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Api
@Controller
@RequestMapping("/catalog")
public class ComponentListController {


    @GetMapping("/component_list")
    public String getComponentList(final Model model, final WebRequest request) {

        // this is the name of the associated Thymeleaf template
        return "component_list";
    }
}
