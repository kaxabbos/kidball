package com.kidball.controller;

import com.kidball.controller.main.Attributes;
import com.kidball.model.Kid;
import com.kidball.model.Parent;
import com.kidball.model.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserCont extends Attributes {
    @GetMapping
    public String users(Model model) {
        AddAttributes(model);
        return "user";
    }

    @PostMapping("/edit")
    public String editUser(@RequestParam String parent, @RequestParam String kid, @RequestParam byte age, @RequestParam String tel) {
        Users user = getUser();
        Parent p = user.getParent();
        p.setParent(parent);
        p.setTel(tel);
        Kid k = user.getKid();
        k.setKid(kid);
        k.setAge(age);
        usersRepo.save(user);
        return "redirect:/user";
    }
}
