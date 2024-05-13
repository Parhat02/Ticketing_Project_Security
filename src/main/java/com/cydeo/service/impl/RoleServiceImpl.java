package com.cydeo.service.impl;

import com.cydeo.dto.RoleDTO;
import com.cydeo.entity.Role;
import com.cydeo.mapper.RoleMapper;
import com.cydeo.repository.RoleRepository;
import com.cydeo.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public List<RoleDTO> listAllRoles() {

        List<Role> roleList = roleRepository.findAll();
        // I have Role entity from DB, and I need to convert it to RoleDTO.
        // I need to use ModelMapper. I already created a class called RoleMapper and there are methods
        // for me that will make this conversion

        return roleList.stream().map(roleMapper::convertToDto).toList();
    }

    @Override
    public RoleDTO findById(Long id) {
        return null;
    }
}
