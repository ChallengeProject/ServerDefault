package me.hackathon.root.exceptions;

import me.hackathon.root.model.supoort.ExceptionCode;

public class NotFoundUserException extends AbstractRuntimeException{

    public NotFoundUserException() {
        super(ExceptionCode.NOT_FOUND_USER);
    }
}
