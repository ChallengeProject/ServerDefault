package me.hackathon.root.exceptions;

import me.hackathon.root.model.supoort.ExceptionCode;

public class UserAlreadyExistsException extends AbstractRuntimeException {

    public UserAlreadyExistsException() {
        super(ExceptionCode.USER_ALREADY_EXISTS, ExceptionCode.USER_ALREADY_EXISTS.getMessage());
    }
}
