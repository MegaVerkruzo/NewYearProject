package com.commercial.backend.service;

import com.commercial.backend.db.UsersRepository;
import com.commercial.backend.db.entities.User;
import com.commercial.backend.model.feedback.Feedback;
import com.commercial.backend.model.game.GameState;
import org.springframework.stereotype.Service;

import static com.commercial.backend.model.ApiException.hugeSizeField;
import static com.commercial.backend.model.ApiException.noFeedback;
import static com.commercial.backend.model.ApiException.noUser;
import static com.commercial.backend.model.ApiException.userExists;
import static com.commercial.backend.model.game.GameState.createEmptyState;
import static com.commercial.backend.model.game.GameState.createStateWithException;

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
    public GameState addNewUserAndGetTokenWithHistory(User user) {
        User searchUser = repository.findUserByPhone(user.getPhone());
        if (searchUser != null) {
            return createStateWithException(userExists);
        }

        if (checkFieldOnSize(user.getPhone())
                && checkFieldOnSize(user.getName())
                && checkFieldOnSize(user.getSurname())
                && checkFieldOnSize(user.getMiddleName())
                && checkFieldOnSize(user.getEmail())
                && checkFieldOnSize(user.getPlace())
        ) {
            repository.insert(user);
            // :TODO change it
            return createEmptyState();
        } else {
            return createStateWithException(hugeSizeField);
        }
    }

    @Override
    public GameState checkTokenWithException(String authorization) {
        String token = getToken(authorization);
        User searchUser = repository.findUserByToken(token);
        // :Change it
        return searchUser == null ? createStateWithException(noUser) : createEmptyState();
    }

    private String getToken(String authorization) {
        return authorization;
    }

    @Override
    public Feedback addFeedback(User user, String feedback) {
        if (user == null) {
            return new Feedback(noUser);
        }

        if (feedback == null || feedback.isEmpty()) {
            return new Feedback(noFeedback);
        }

        repository.insertFeedbackByPhone(user.getPhone(), feedback);
        return null;
    }

    @Override
    public User getUserByToken(String token) {
        return repository.findUserByToken(token);
    }
}
