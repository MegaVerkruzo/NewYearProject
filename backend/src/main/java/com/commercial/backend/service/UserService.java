package com.commercial.backend.service;

import com.commercial.backend.model.User;
import com.commercial.backend.db.UsersRepository;
import com.commercial.backend.security.JWTUtil;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

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
        return Pair.of(JWTUtil.generateToken(user), userContained);
    }
}
