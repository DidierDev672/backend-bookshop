package org.acme.applications.user.port;

import org.acme.applications.shared.CustomException;

import java.util.Optional;

public interface UserCreatePort<T> {
    Optional<T> createUser(T entity) throws CustomException;
}
