package com.commercial.backend.service;

import com.commercial.backend.model.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();
}
