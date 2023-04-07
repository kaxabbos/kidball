package com.kidball.repo;

import com.kidball.model.Trainers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainersRepo extends JpaRepository<Trainers, Long> {

}
