package com.kidball.controller;

import com.kidball.controller.main.Attributes;
import com.kidball.model.Trainers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/trainers")
public class TrainersCont extends Attributes {

    @GetMapping
    public String Trainers(Model model) {
        AddAttributesTrainers(model);
        return "trainers";
    }

    @GetMapping("/add")
    public String trainerAdd(Model model) {
        AddAttributes(model);
        return "trainerAdd";
    }

    @GetMapping("/delete/{id}")
    public String TrainerDelete(Model model, @PathVariable Long id) {
        trainersRepo.deleteById(id);
        return "redirect:/trainers";
    }

    @PostMapping("/add")
    public String trainerAdd(Model model, @RequestParam String name, @RequestParam String tel, @RequestParam String career, @RequestParam String achievements, @RequestParam String age, @RequestParam MultipartFile file) {
        String res = "";
        if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
            String uuidFile = UUID.randomUUID().toString();
            boolean createDir = true;
            try {
                File uploadDir = new File(uploadImg);
                if (!uploadDir.exists()) createDir = uploadDir.mkdir();
                if (createDir) {
                    res = "trainers/" + uuidFile + "_" + file.getOriginalFilename();
                    file.transferTo(new File(uploadImg + "/" + res));
                }
            } catch (Exception e) {
                model.addAttribute("message", "Некорректный данные!");
                AddAttributes(model);
                return "trainerAdd";
            }
        }

        Trainers trainer = new Trainers(name, tel, career, achievements, age, res);
        trainersRepo.save(trainer);

        return "redirect:/trainers/add";
    }

    @GetMapping("/edit/{id}")
    public String trainerEdit(Model model, @PathVariable Long id) {
        AddAttributesTrainerEdit(model, id);
        return "trainerEdit";
    }

    @PostMapping("/edit/{id}")
    public String trainerEdit(Model model, @RequestParam String name, @RequestParam String tel, @RequestParam String career, @RequestParam String achievements, @RequestParam String age, @RequestParam MultipartFile file, @PathVariable Long id) {
        Trainers trainer = trainersRepo.getReferenceById(id);

        trainer.setName(name);
        trainer.setTel(tel);
        trainer.setAge(age);
        trainer.setCareer(career);
        trainer.setAchievements(achievements);

        if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
            String res = "";
            String uuidFile = UUID.randomUUID().toString();
            boolean createDir = true;
            try {
                File uploadDir = new File(uploadImg);
                if (!uploadDir.exists()) createDir = uploadDir.mkdir();
                if (createDir) {
                    res = "trainers/" + uuidFile + "_" + file.getOriginalFilename();
                    file.transferTo(new File(uploadImg + "/" + res));
                }
            } catch (Exception e) {
                AddAttributesTrainerEdit(model, id);
                model.addAttribute("message", "Некорректный данные!");
                return "trainerEdit";
            }
            trainer.setFile(res);
        }

        trainersRepo.save(trainer);
        return "redirect:/trainers";
    }
}
