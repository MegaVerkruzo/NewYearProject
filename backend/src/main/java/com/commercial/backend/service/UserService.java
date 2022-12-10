package com.commercial.backend.service;

import com.commercial.backend.model.User;
import com.commercial.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    private final UserRepository repository;

    UserService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }
}
