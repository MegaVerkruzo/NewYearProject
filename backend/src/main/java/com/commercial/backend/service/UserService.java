package com.commercial.backend.service;

import com.commercial.backend.model.User;
import com.commercial.backend.db.UsersRepository;
import com.commercial.backend.security.PasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService implements IUserService {
    private final UsersRepository repository;

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    UserService(UsersRepository usersRepository) {
        this.repository = usersRepository;
    }

    private boolean checkFieldOnSize(String str) {
        return str.length() < 250;
    }

    @Override
    public Pair<String, String> addNewUserAndGetTokenWithHistory(User user) {
        User searchUser = repository.findUserByPhone(user.getPhone());
        if (searchUser == null) {
            if (checkFieldOnSize(user.getPhone()) && checkFieldOnSize(user.getName()) && checkFieldOnSize(user.getSurname()) && checkFieldOnSize(user.getMiddleName()) && checkFieldOnSize(user.getEmail()) && checkFieldOnSize(user.getPlace())) {
                repository.insert(user);
                return Pair.of(user.getToken(), "");
            } else {
                return Pair.of("", "hugeSizeField");
            }
        } else {
            return Pair.of("", "userExists");
        }
    }

    @Override
    public Pair<String, String> getTokenWithCheckingPassword(User user, String rawPassword) {
        User searchUser = repository.findUserByPhone(user.getPhone());
        if (searchUser == null || !PasswordEncoder.checkHash(rawPassword, searchUser.getPasswordHash())) {
            return Pair.of("", "noUser");
        }

        return Pair.of(user.getToken(), "");
    }

    @Override
    public Pair<String, String> checkTokenWithException(String token) {
        User searchUser = repository.findUserByToken(token);
        if (searchUser == null) {
            return Pair.of("", "noUser");
        } else {
            return Pair.of(token, "");
        }
    }

    @Override
    public Pair<String, String> addFeedback(String token, String feedback) {
        User user = repository.findUserByToken(token);

        logger.info("Find user " + user);

        if (user == null) {
            return Pair.of("", "noUser");
        }

        if (user.getFeedback() != null) {
            logger.info("Feedback already exists");
            return Pair.of("exception", "hadFeedback");
        }

        repository.insertFeedbackByToken(token, feedback);
        return Pair.of("exception", "");
    }
}
