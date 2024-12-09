package org.acme.applications.user.port;

import org.acme.applications.shared.CustomException;

import java.util.Optional;

public interface TokenPort <T>{
    Optional<T> createToken(String username) throws CustomException;

}
