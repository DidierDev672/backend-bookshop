package org.acme.applications.author.ports;

import org.acme.applications.shared.CustomException;

import java.util.Optional;

public interface AuthorCreatePort<T, R> {
    Optional<T> create(R entity) throws CustomException;
}
