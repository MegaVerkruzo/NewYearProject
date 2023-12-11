package com.commercial.backend.service.impls;

import com.commercial.backend.db.UserRepository;
import com.commercial.backend.db.entities.User;
import com.commercial.backend.model.feedback.Feedback;
import com.commercial.backend.model.game.GameStateKlass;
import com.commercial.backend.security.exception.NotFoundUserException;
import com.commercial.backend.security.exception.UserExistsException;
import com.commercial.backend.service.interfaces.IUserService;
import org.springframework.stereotype.Service;

import static com.commercial.backend.model.game.GameStateKlass.createEmptyState;
import static com.commercial.backend.model.game.GameStateKlass.createStateWithException;
import static com.commercial.backend.security.ApiException.hugeSizeField;
import static com.commercial.backend.security.ApiException.noFeedback;
import static com.commercial.backend.security.ApiException.notRegistered;

@Service
public class UserService implements IUserService {
    private final UserRepository repository;

    UserService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    private boolean checkFieldOnSize(String str) {
        return str.length() < 250;
    }

    @Override
    public GameStateKlass addNewUserAndGetTokenWithHistory(User user) {
        repository.findUserByPhone(user.getPhone()).ifPresent(UserExistsException::new);

        if (checkFieldOnSize(user.getPhone())
                && checkFieldOnSize(user.getName())
                && checkFieldOnSize(user.getSurname())
                && checkFieldOnSize(user.getMiddleName())
                && checkFieldOnSize(user.getEmail())
                && checkFieldOnSize(user.getPlace())
        ) {
            repository.save(user);
            // :TODO change it
            return createEmptyState();
        } else {
            return createStateWithException(hugeSizeField);
        }
    }

    @Override
    public GameStateKlass checkTokenWithException(String authorization) {
        String token = getToken(authorization);
        repository.findUserByPhone(token).orElseThrow(NotFoundUserException::new);
        // :TODO Change it
        return createEmptyState();
    }

    private String getToken(String authorization) {
        return authorization;
    }

    @Override
    public Feedback addFeedback(User user, String feedback) {
        if (user == null) {
            return new Feedback(notRegistered);
        }

        if (feedback == null || feedback.isEmpty()) {
            return new Feedback(noFeedback);
        }

        repository.updateFeedbackByPhone(feedback, user.getId());
        return null;
    }

    @Override
    public User getUserByToken(String token) {
        return repository.findUserByPhone(token).orElseThrow(NotFoundUserException::new);
    }
}
