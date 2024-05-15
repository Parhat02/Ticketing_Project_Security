package com.cydeo.service;

import com.cydeo.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> listAllUsers(); // This method will be called in the controller, so we use dto

    UserDTO findByUserName(String username);

    void save(UserDTO user);

    void deleteByUserName(String username);

    UserDTO update(UserDTO user);

}
