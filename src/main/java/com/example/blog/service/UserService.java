package com.example.blog.service;
import com.example.blog.entity.User;
import com.example.blog.exception.*;

import com.example.blog.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Objects;
import java.util.List;
@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findById(Integer id) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("Id null when fetching for an user.");
        }
        return userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException(
                        String.format("No user found for id %d", id))
        );
    }

    public User add(User user) {
        if (Objects.isNull(user) || Objects.isNull(user.getName())
                || Objects.isNull(user.getUsername()) || Objects.isNull(user.getEmail())) {
            throw new InvalidUserException("Invalid user.");
        }
        return userRepository.save(user);
    }
}
