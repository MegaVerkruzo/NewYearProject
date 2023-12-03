package com.commercial.backend.service;

import com.commercial.backend.db.UsersRepository;
import com.commercial.backend.model.TokenException;
import com.commercial.backend.model.User;
import com.commercial.backend.security.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.commercial.backend.Common.pairToMap;
import static com.commercial.backend.model.AuthException.CORRECT;
import static com.commercial.backend.model.AuthException.HUGE_SIZE_FIELD;
import static com.commercial.backend.model.AuthException.NO_USER;
import static com.commercial.backend.model.AuthException.USER_EXISTS;

@Service
public class UserService implements IUserService {
    private final UsersRepository repository;

    UserService(UsersRepository usersRepository) {
        this.repository = usersRepository;
    }

    private boolean checkFieldOnSize(String str) {
        return str.length() < 250;
    }

    @Override
    public TokenException addNewUserAndGetTokenWithHistory(User user) {
        User searchUser = repository.findUserByPhone(user.getPhone());
        if (searchUser != null) {
            return new TokenException(null, USER_EXISTS);
        }

        if (checkFieldOnSize(user.getPhone())
                && checkFieldOnSize(user.getName())
                && checkFieldOnSize(user.getSurname())
                && checkFieldOnSize(user.getMiddleName())
                && checkFieldOnSize(user.getEmail())
                && checkFieldOnSize(user.getPlace())
        ) {
            repository.insert(user);
            return new TokenException(user.getToken(), CORRECT);
        } else {
            return new TokenException(null, HUGE_SIZE_FIELD);
        }
    }

    @Override
    public TokenException getTokenWithCheckingPassword(User user, String rawPassword) {
        User searchUser = repository.findUserByPhone(user.getPhone());
        if (searchUser == null || !PasswordEncoder.checkHash(rawPassword, searchUser.getPasswordHash())) {
            return new TokenException(null, NO_USER);
        }

        return new TokenException(user.getToken(), CORRECT);
    }

    @Override
    public TokenException checkTokenWithException(String token) {
        User searchUser = repository.findUserByToken(token);
        return searchUser == null ? new TokenException(null, NO_USER) : new TokenException(token, CORRECT);
    }

    @Override
    public Map<String, Object> addFeedback(User user, String feedback) {
        if (user == null) {
            return pairToMap("exception", "noUser");
        }

        if (feedback == null || feedback.isEmpty()) {
            return pairToMap("exception", "noFeedback");
        }

        repository.insertFeedbackByPhone(user.getPhone(), feedback);
        return pairToMap("exception", "");
    }

    @Override
    public User getUserByToken(String token) {
        return repository.findUserByToken(token);
    }
}
