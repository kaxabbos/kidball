package com.kidball.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.User;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Carts {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Users owner;
    @ManyToOne(fetch = FetchType.LAZY)
    private Subs sub;
    @ManyToOne(fetch = FetchType.LAZY)
    private Trainers trainer;
}
