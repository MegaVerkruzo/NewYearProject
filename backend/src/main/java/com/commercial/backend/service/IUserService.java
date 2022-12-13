package com.commercial.backend.service;

import com.commercial.backend.model.User;
import org.springframework.data.util.Pair;

public interface IUserService {
    Pair<String, Boolean> addNewUserAndGetTokenWithHistory(User user);

    Pair<String, String> getTokenWithCheckingPassword(User user, String rawPassword);
}
