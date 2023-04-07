package com.kidball.controller;

import com.kidball.controller.main.Attributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexCont extends Attributes {

    @GetMapping
    public String index1(Model model) {
        AddAttributesSubs(model);
        return "subs";
    }

    @GetMapping("/index")
    public String index2(Model model) {
        AddAttributesSubs(model);
        return "subs";
    }
}
