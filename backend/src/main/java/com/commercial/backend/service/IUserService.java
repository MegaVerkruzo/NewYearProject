package com.commercial.backend.service;

import com.commercial.backend.model.User;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IUserService {
    Pair<String, Boolean> addNewUserAndGetTokenWithHistory(User user);
}
