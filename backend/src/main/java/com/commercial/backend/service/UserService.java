package com.commercial.backend.service;

import com.commercial.backend.model.User;
import com.commercial.backend.db.UsersRepository;
import com.commercial.backend.security.PasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    private final UsersRepository repository;

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    UserService(UsersRepository usersRepository) {
        this.repository = usersRepository;
    }

    @Override
    public Pair<String, Boolean> addNewUserAndGetTokenWithHistory(User user) {
        User searchUser = repository.findUserByPhone(user.getPhone());
        boolean userContained = false;
        if (searchUser == null) {
            repository.insert(user);
        } else {
            user = searchUser;
            userContained = true;
        }
        return Pair.of(user.getToken(), userContained);
    }

    @Override
    public Pair<String, String> getTokenWithCheckingPassword(User user, String rawPassword) {
        User searchUser = repository.findUserByPhone(user.getPhone());
        if (searchUser == null) {
            return Pair.of("", "noUser");
        }
        if (PasswordEncoder.checkHash(rawPassword, searchUser.getPasswordHash())) {
            return Pair.of(user.getToken(), "");
        } else {
            return Pair.of("", "incorrectPassword");
        }
    }
}
