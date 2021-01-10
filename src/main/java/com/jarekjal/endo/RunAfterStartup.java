package com.jarekjal.endo;

import com.jarekjal.endo.controllers.Role;
import com.jarekjal.endo.repo.UserRepo;
import com.jarekjal.endo.repo.UserRoleRepo;
import com.jarekjal.endo.repo.entities.User;
import com.jarekjal.endo.repo.entities.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RunAfterStartup {

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserRoleRepo userRoleRepo;

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        System.out.println("STARTUP ACTION: Creating admin user with ROLE_ADMIN...");
        userRepo.save(new User("admin", "admin", true));
        userRoleRepo.save(new UserRole("admin", Role.ROLE_ADMIN.name()));
        System.out.println("STARTUP ACTION: Admin created.");
    }
}
