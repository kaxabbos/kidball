package com.kidball.repo;

import com.kidball.model.Carts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartsRepo extends JpaRepository<Carts, Long> {

}
