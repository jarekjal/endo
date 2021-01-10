package com.jarekjal.endo.repo;

import com.jarekjal.endo.repo.entities.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepo extends CrudRepository<UserRole, Long> {
}
