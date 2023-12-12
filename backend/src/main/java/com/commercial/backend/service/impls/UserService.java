package com.commercial.backend.service.impls;

import com.commercial.backend.db.UserRepository;
import com.commercial.backend.db.entities.User;
import com.commercial.backend.model.feedback.Feedback;
import com.commercial.backend.model.json.JsonUser;
import com.commercial.backend.model.state.State;
import com.commercial.backend.model.state.period.BeforeGameState;
import com.commercial.backend.security.exception.NotRegisteredException;
import com.commercial.backend.security.exception.NotValidException;
import com.commercial.backend.security.exception.UserExistsException;
import com.commercial.backend.service.interfaces.IUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import static com.commercial.backend.security.ApiException.noFeedback;
import static com.commercial.backend.security.ApiException.notRegistered;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;

    private boolean checkFieldOnSize(String str) {
        return str.length() < 250;
    }

    @Override
    public State registerNewUser(User user) {
        userRepository.findUserById(user.getId()).ifPresent(UserExistsException::new);

        if (checkFieldOnSize(user.getPhone())
                && checkFieldOnSize(user.getName())
                && checkFieldOnSize(user.getSurname())
                && checkFieldOnSize(user.getMiddleName())
                && checkFieldOnSize(user.getEmail())
                && checkFieldOnSize(user.getPlace())
        ) {
            userRepository.save(user);
            // :TODO change it
            return new BeforeGameState();
        } else {
            throw new NotValidException();
        }
    }

    @Override
    public State getState(String authorization) throws NotRegisteredException {
        userRepository
                .findUserById(parseId(authorization))
                .orElseThrow(NotRegisteredException::new);
        // :TODO ad-hoc
        return new BeforeGameState();
    }

    @Override
    public Feedback addFeedback(User user, String feedback) {
        if (user == null) {
            return new Feedback(notRegistered);
        }

        if (feedback == null || feedback.isEmpty()) {
            return new Feedback(noFeedback);
        }

        userRepository.updateFeedbackByPhone(feedback, user.getId());
        return null;
    }

    @Override
    public User getUserByToken(String token) throws NotRegisteredException, NotValidException {
        return userRepository.findUserById(parseId(token)).orElseThrow(NotRegisteredException::new);
    }

    public static Long parseId(String token) throws NotRegisteredException, NotValidException {
        if (token == null) {
            throw new NotValidException();
        }
        String userJson = URLDecoder.decode(token, StandardCharsets.UTF_8).split("&")[1].split("=")[1];
        ObjectMapper mapper = new ObjectMapper();
        Long result;
        try {
            result = mapper.readValue(userJson, JsonUser.class).getId();
        } catch (JsonProcessingException e) {
             throw new NotValidException();
        }
        return result;
    }
}
