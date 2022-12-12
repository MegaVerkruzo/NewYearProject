package com.commercial.backend.service;

import com.commercial.backend.model.User;
import com.commercial.backend.model.Token;
import com.commercial.backend.db.UsersRepository;
import com.commercial.backend.security.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    private final UsersRepository repository;

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
        Token token = new Token(JWTUtil.generateToken(user));
        return Pair.of(token.getToken(), userContained);
    }
}
