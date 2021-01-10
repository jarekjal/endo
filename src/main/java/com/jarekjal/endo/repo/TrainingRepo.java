package com.jarekjal.endo.repo;

import com.jarekjal.endo.repo.entities.Training;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TrainingRepo extends CrudRepository<Training, Long> {

    List<Training> findByUserName(String username);

}
