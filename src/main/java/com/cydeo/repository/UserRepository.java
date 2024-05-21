package com.cydeo.repository;

import com.cydeo.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //get user based on username
    User findByUserName(String username);

    @Transactional
    void deleteByUserName(String userName);

    //List<User> findAllByIsDeleted(Boolean isDeleted);

    List<User> findByRoleDescriptionIgnoreCase(String description);


}
