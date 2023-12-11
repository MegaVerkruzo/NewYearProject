package com.commercial.backend.security;

import com.commercial.backend.exception.no.user.NotFoundUserException;
import com.commercial.backend.exception.no.user.NotFoundUserResponse;
import com.commercial.backend.exception.user.exists.UserExistsException;
import com.commercial.backend.exception.user.exists.UserExistsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class SecurityControllerAdvice {
    @ExceptionHandler(NotFoundUserException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public NotFoundUserResponse handleNotFoundUserException(NotFoundUserException ignored) {
        return new NotFoundUserResponse();
    }

    @ExceptionHandler(UserExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public UserExistsResponse handleUserExistsException(UserExistsException ignored) {
        return new UserExistsResponse();
    }
}