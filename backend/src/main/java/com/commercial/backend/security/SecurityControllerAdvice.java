package com.commercial.backend.security;

import com.commercial.backend.security.exception.AlreadyExist5AttemptsException;
import com.commercial.backend.security.exception.AlreadyExistCorrectAttemptException;
import com.commercial.backend.security.exception.BadRequestException;
import com.commercial.backend.security.exception.NoWordInDictionaryException;
import com.commercial.backend.security.exception.NotFoundUserException;
import com.commercial.backend.security.exception.NotValidException;
import com.commercial.backend.security.exception.UserExistsException;
import com.commercial.backend.security.response.AlreadyExist5AttemptsResponse;
import com.commercial.backend.security.response.AlreadyExistCorrectAttemptResponse;
import com.commercial.backend.security.response.BadRequestResponse;
import com.commercial.backend.security.response.NoWordInDictionaryResponse;
import com.commercial.backend.security.response.NotFoundUserResponse;
import com.commercial.backend.security.response.NotValidResponse;
import com.commercial.backend.security.response.UserExistsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class SecurityControllerAdvice {
    @ExceptionHandler(AlreadyExist5AttemptsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AlreadyExist5AttemptsResponse handleUserExistsException(AlreadyExist5AttemptsException ignored) {
        return new AlreadyExist5AttemptsResponse();
    }

    @ExceptionHandler(AlreadyExistCorrectAttemptException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AlreadyExistCorrectAttemptResponse handleUserExistsException(AlreadyExistCorrectAttemptException ignored) {
        return new AlreadyExistCorrectAttemptResponse();
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BadRequestResponse handleUserExistsException(BadRequestException ignored) {
        return new BadRequestResponse();
    }

    @ExceptionHandler(NotFoundUserException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public NotFoundUserResponse handleNotFoundUserException(NotFoundUserException ignored) {
        return new NotFoundUserResponse();
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
}