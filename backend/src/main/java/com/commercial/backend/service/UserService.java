package com.commercial.backend.service;

import com.commercial.backend.db.FeedbackRepository;
import com.commercial.backend.db.UserRepository;
import com.commercial.backend.db.entities.Feedback;
import com.commercial.backend.db.entities.User;
import com.commercial.backend.model.json.JsonFeedback;
import com.commercial.backend.model.state.State;
import com.commercial.backend.security.exception.BadRequestException;
import com.commercial.backend.security.exception.NotRegisteredException;
import com.commercial.backend.security.exception.NotValidException;
import com.commercial.backend.security.exception.UserExistsException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.commercial.backend.service.CommonService.parseId;

@Service
@AllArgsConstructor
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final AttemptService attemptService;
    private final FeedbackRepository feedbackRepository;

    private boolean checkFieldOnSize(String str) {
        return str.length() < 250;
    }

    public State registerNewUser(User user) {
        userRepository.findUserById(user.getId()).ifPresent(UserExistsException::new);

        logger.info("user.getId(): " + user.getId());
        logger.info("user.getId(): " + user.getId());

        if (checkFieldOnSize(user.getPhone())
                && checkFieldOnSize(user.getName())
                && checkFieldOnSize(user.getSurname())
                && checkFieldOnSize(user.getMiddleName())
                && checkFieldOnSize(user.getEmail())
                && checkFieldOnSize(user.getPlace())
        ) {
            userRepository.save(user);

            return new State();
        } else {
            throw new NotValidException();
        }
    }

    public State getState(String authorization) throws NotRegisteredException {
        User user = userRepository
                .findUserById(parseId(authorization))
                .orElseThrow(NotRegisteredException::new);

        return attemptService.getState(user);
    }

    public State addFeedback(String authorization, JsonFeedback response) throws BadRequestException {
        User user = userRepository
                .findUserById(parseId(authorization))
                .orElseThrow(NotRegisteredException::new);

        if (response == null || response.getFeedback().isEmpty()) {
            throw new NotValidException();
        }
        Feedback feedback = feedbackRepository
                .findFeedbackByUserId(user.getId())
                .stream()
                .filter(fb -> fb.getResponse() == null)
                .findAny()
                .orElseThrow(BadRequestException::new);

        feedbackRepository.updateFeedbackById(response.getFeedback(), feedback.getId());
        return new State();
    }

    public User getUserByToken(String token) throws NotRegisteredException, NotValidException {
        return userRepository.findUserById(parseId(token)).orElseThrow(NotRegisteredException::new);
    }
}
