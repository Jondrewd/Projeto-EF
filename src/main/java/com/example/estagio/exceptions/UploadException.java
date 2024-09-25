package com.example.estagio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UploadException extends MultipartException{
    
    private static final long serialVersionUID =1L;

    public UploadException(String msg) {
        super(msg);
    }
}
