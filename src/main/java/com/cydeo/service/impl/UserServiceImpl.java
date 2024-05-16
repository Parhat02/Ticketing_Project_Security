package com.cydeo.service.impl;

import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;
import com.cydeo.mapper.UserMapper;
import com.cydeo.repository.UserRepository;
import com.cydeo.service.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> listAllUsers() {

        List<User>  userList=userRepository.findAll((Sort.by("firstName")));
        return userList.stream().map(userMapper::convertToDto).toList();
    }

    @Override
    public UserDTO findByUserName(String username) {

        return userMapper.convertToDto(userRepository.findByUserName(username));
    }

    @Override
    public void save(UserDTO user) {
        User user1 = userMapper.convertToEntity(user);
        userRepository.save(user1);
    }

    @Override
    public void deleteByUserName(String username) {
//        User user1 = userRepository.findByUserName(username);
//        userRepository.deleteById(user1.getId());
        userRepository.deleteByUserName(username);
    }

    @Override
    public UserDTO update(UserDTO user) {

        User user1 = userRepository.findByUserName(user.getUserName());

        User convertedUser = userMapper.convertToEntity(user);

        convertedUser.setId(user1.getId());

        userRepository.save(convertedUser);

        return findByUserName(user.getUserName());
    }

    @Override
    public void delete(String username) {

        User user1 = userRepository.findByUserName(username);
        user1.setIsDeleted(true);
        userRepository.save(user1);

    }

    @Override
    public List<UserDTO> listAllByRole(String role) {

        List<User> users = userRepository.findByRoleDescriptionIgnoreCase(role);

        return users.stream().map(userMapper::convertToDto).toList();
    }
}
