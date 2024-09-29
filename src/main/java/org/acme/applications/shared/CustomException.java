package org.acme.applications.shared;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomException extends RuntimeException {
    public CustomException(String message){
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }
}
