package com.kidball.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Stats {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Price price;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Count count;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Income income;

    public Stats(int price) {
        this.price = new Price(price);
        this.count = new Count();
        this.income = new Income();
    }
}
