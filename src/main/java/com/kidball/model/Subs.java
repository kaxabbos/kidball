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
public class Subs {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private byte period;
    private byte pause;
    private String file;
    @Column(length = 5000)
    private String description;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Stats stat;
    @OneToMany(mappedBy = "sub", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Carts> carts;

    public Subs(String name, byte period, byte pause, String file, int price, String description) {
        this.name = name;
        this.period = period;
        this.pause = pause;
        this.file = file;
        this.stat = new Stats(price);
        this.description = description;
    }

    public void addCart(Carts cart) {
        carts.add(cart);
        cart.setSub(this);
    }
}
