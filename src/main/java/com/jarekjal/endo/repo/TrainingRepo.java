package com.jarekjal.endo.repo;

import com.jarekjal.endo.repo.entities.Training;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingRepo extends CrudRepository<Training, Long> {

    List<Training> findByUserName(String username);

}
