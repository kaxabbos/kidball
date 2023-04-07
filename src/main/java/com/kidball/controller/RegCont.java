package com.kidball.controller;

import com.kidball.controller.main.Attributes;
import com.kidball.model.Users;
import com.kidball.model.enums.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reg")
public class RegCont extends Attributes {

    @GetMapping
    public String reg() {
        return "reg";
    }

    @PostMapping
    public String regUser(Model model, @RequestParam String username, @RequestParam String password, @RequestParam String parent, @RequestParam String kid, @RequestParam byte age, @RequestParam String tel) {
        if (usersRepo.findAll().isEmpty() || usersRepo.findAll().size() == 0) {
            usersRepo.save(new Users(Role.ADMIN, username, password, parent, kid, age, tel));
            return "redirect:/login";
        }

        if (usersRepo.findByUsername(username) != null) {
            model.addAttribute("message", "Пользователь с таким логином уже существует!");
            AddAttributes(model);
            return "reg";
        }

        usersRepo.save(new Users(Role.CLIENT, username, password, parent, kid, age, tel));

        return "redirect:/login";
    }
}
