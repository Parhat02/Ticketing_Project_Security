package com.cydeo.service;

import com.cydeo.dto.RoleDTO;

import java.util.List;

public interface RoleService {

    List<RoleDTO> listAllRoles(); // This is Server side not repository, so we use dto here

    RoleDTO findById(Long id);
}
