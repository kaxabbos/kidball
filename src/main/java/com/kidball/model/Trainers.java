package com.kidball.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Trainers {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String name;
    private String tel;
    private String career;
    private String achievements;
    private String age;
    private String file;
    @OneToMany(mappedBy = "trainer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Carts> carts;

    public Trainers(String name, String tel, String career, String achievements, String age, String file) {
        this.name = name;
        this.tel = tel;
        this.career = career;
        this.achievements = achievements;
        this.age = age;
        this.file = file;
    }

    public void addCart(Carts cart) {
        carts.add(cart);
        cart.setTrainer(this);
    }
}
