package com.kidball.controller.main;

import com.kidball.model.enums.Role;
import org.springframework.ui.Model;

public class Attributes extends Main {

    protected void AddAttributes(Model model) {
        model.addAttribute("role", getRole());
        model.addAttribute("user", getUser());
    }

    protected void AddAttributesUsers(Model model) {
        AddAttributes(model);
        model.addAttribute("users", usersRepo.findAllByOrderByRole());
        model.addAttribute("roles", Role.values());
    }

    protected void AddAttributesAbout(Model model) {
        AddAttributes(model);
    }

    protected void AddAttributesIndex(Model model) {
        AddAttributes(model);
        model.addAttribute("trainers", trainersRepo.findAll());
    }

    protected void AddAttributesTrainerEdit(Model model, Long id) {
        AddAttributes(model);
        model.addAttribute("trainer", trainersRepo.getReferenceById(id));
    }

    protected void AddAttributesSubs(Model model) {
        AddAttributes(model);
        model.addAttribute("subs", subsRepo.findAll());
    }

    protected void AddAttributesSub(Model model, Long id) {
        AddAttributes(model);
        model.addAttribute("sub", subsRepo.getReferenceById(id));
        model.addAttribute("trainers", trainersRepo.findAll());
        model.addAttribute("users", usersRepo.findAllByRole(Role.CLIENT));
    }

    protected void AddAttributesSubsMy(Model model) {
        AddAttributes(model);
        model.addAttribute("carts", getUser().getCarts());
    }

    protected void AddAttributesTrainers(Model model) {
        AddAttributes(model);
        model.addAttribute("trainers", trainersRepo.findAll());
    }

    protected void AddAttributesSubEdit(Model model, Long id) {
        AddAttributes(model);
        model.addAttribute("sub", subsRepo.getReferenceById(id));
    }

    protected void AddAttributesStats(Model model) {
        AddAttributes(model);
        model.addAttribute("subs", subsRepo.findAll());
    }
}
