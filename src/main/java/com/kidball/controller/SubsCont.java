package com.kidball.controller;

import com.kidball.controller.main.Attributes;
import com.kidball.model.Carts;
import com.kidball.model.Subs;
import com.kidball.model.Trainers;
import com.kidball.model.Users;
import com.kidball.repo.CartsRepo;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/subs")
public class SubsCont extends Attributes {
    @GetMapping
    public String Subs(Model model) {
        AddAttributesSubs(model);
        return "subs";
    }

    @GetMapping("/{id}")
    public String Sub(Model model, @PathVariable Long id) {
        AddAttributesSub(model, id);
        return "sub";
    }

    @GetMapping("/my")
    public String SubsMy(Model model) {
        AddAttributesSubsMy(model);
        return "subsMy";
    }

    @GetMapping("/my/delete/{id}")
    public String SubMyDelete(@PathVariable Long id) {
        cartsRepo.deleteById(id);
        return "redirect:/subs/my";
    }

    @PostMapping("/buy/{id}")
    public String SubBuy(Model model, @PathVariable Long id, @RequestParam Long userId, @RequestParam Long trainerId) {
        Subs sub = subsRepo.getReferenceById(id);
        Trainers trainer = trainersRepo.getReferenceById(trainerId);
        Users user = usersRepo.getReferenceById(userId);

        for (Carts i : user.getCarts()) {
            if (i.getSub().getId().equals(sub.getId())) {
                AddAttributesSub(model, id);
                model.addAttribute("message", "Такой абонемент уже оформлен!");
                return "sub";
            }
        }

        Carts cart = cartsRepo.save(new Carts());

        user.addCart(cart);
        trainer.addCart(cart);
        sub.addCart(cart);

        sub.getStat().getCount().setCount(sub.getStat().getCount().getCount() + 1);
        sub.getStat().getIncome().setIncome(sub.getStat().getIncome().getIncome() + sub.getStat().getPrice().getPrice());
        subsRepo.save(sub);

        return "redirect:/subs/{id}";
    }

    @GetMapping("/add")
    public String SubAdd(Model model) {
        AddAttributes(model);
        return "subAdd";
    }

    @GetMapping("/edit/{id}")
    public String SubEdit(Model model, @PathVariable Long id) {
        AddAttributesSubEdit(model, id);
        return "subEdit";
    }

    @GetMapping("/delete/{id}")
    public String SubDelete(@PathVariable Long id) {
        subsRepo.deleteById(id);
        return "redirect:/subs";
    }

    @PostMapping("/add")
    public String subsAddNew(Model model, @RequestParam String name, @RequestParam byte period, @RequestParam byte pause, @RequestParam MultipartFile file, @RequestParam int price, @RequestParam String description) {
        String res = "";
        if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
            String uuidFile = UUID.randomUUID().toString();
            boolean createDir = true;
            try {
                File uploadDir = new File(uploadImg);
                if (!uploadDir.exists()) createDir = uploadDir.mkdir();
                if (createDir) {
                    res = "subs/" + uuidFile + "_" + file.getOriginalFilename();
                    file.transferTo(new File(uploadImg + "/" + res));
                }
            } catch (Exception e) {
                model.addAttribute("message", "Некорректный данные!");
                AddAttributes(model);
                return "subAdd";
            }
        }

        Subs sub = new Subs(name, period, pause, res, price, description);

        subsRepo.save(sub);

        return "redirect:/subs/add";
    }

    @PostMapping("/edit/{id}")
    public String SubEditOld(Model model, @RequestParam String name, @RequestParam byte period, @RequestParam byte pause, @RequestParam MultipartFile file, @RequestParam int price, @RequestParam String description, @PathVariable Long id) {
        Subs sub = subsRepo.getReferenceById(id);

        sub.setName(name);
        sub.setPause(pause);
        sub.setPeriod(period);
        sub.setDescription(description);
        sub.getStat().getPrice().setPrice(price);

        if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
            String res = "";
            String uuidFile = UUID.randomUUID().toString();
            boolean createDir = true;
            try {
                File uploadDir = new File(uploadImg);
                if (!uploadDir.exists()) createDir = uploadDir.mkdir();
                if (createDir) {
                    res = "subs/" + uuidFile + "_" + file.getOriginalFilename();
                    file.transferTo(new File(uploadImg + "/" + res));
                }
            } catch (Exception e) {
                model.addAttribute("message", "Некорректный данные!");
                AddAttributesSubEdit(model, id);
                return "subEdit";
            }
            sub.setFile(res);
        }

        subsRepo.save(sub);

        return "redirect:/subs/{id}";
    }
}
