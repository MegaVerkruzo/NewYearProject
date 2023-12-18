package com.commercial.backend.security;

import com.commercial.backend.security.exception.BadRequestException;
import com.commercial.backend.security.exception.NoWordInDictionaryException;
import com.commercial.backend.security.exception.NotRegisteredException;
import com.commercial.backend.security.exception.NotValidException;
import com.commercial.backend.security.exception.OldStateException;
import com.commercial.backend.security.exception.UserExistsException;
import com.commercial.backend.security.response.BadRequestResponse;
import com.commercial.backend.security.response.NoWordInDictionaryResponse;
import com.commercial.backend.security.response.NotRegisteredResponse;
import com.commercial.backend.security.response.NotValidResponse;
import com.commercial.backend.security.response.OldStateResponse;
import com.commercial.backend.security.response.UserExistsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class SecurityControllerAdvice {
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BadRequestResponse handleUserExistsException(BadRequestException ignored) {
        return new BadRequestResponse();
    }

    @ExceptionHandler(NotRegisteredException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public NotRegisteredResponse handleNotFoundUserException(NotRegisteredException ignored) {
        return new NotRegisteredResponse();
    }

    @ExceptionHandler(NotValidException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public NotValidResponse handleNotFoundUserException(NotValidException ignored) {
        return new NotValidResponse();
    }

    @ExceptionHandler(NoWordInDictionaryException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public NoWordInDictionaryResponse handleNotFoundUserException(NoWordInDictionaryException ignored) {
        return new NoWordInDictionaryResponse();
    }

    @ExceptionHandler(UserExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public UserExistsResponse handleUserExistsException(UserExistsException ignored) {
        return new UserExistsResponse();
    }

    @ExceptionHandler(OldStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public OldStateResponse handleUserExistsException(OldStateException ignored) {
        return new OldStateResponse();
    }
}