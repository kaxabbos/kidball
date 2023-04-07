package com.kidball.controller;

import com.kidball.controller.main.Attributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/about")
public class AboutCont extends Attributes {
    @GetMapping
    public String index1(Model model) {
        AddAttributesAbout(model);
        return "about";
    }
}
