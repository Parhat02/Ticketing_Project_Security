package com.cydeo.repository;

import com.cydeo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    // give me a role based on description
    List<Role> findByDescription(String description);


}
