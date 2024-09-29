package org.acme.applications.Genre.ports;

import org.acme.applications.shared.CustomException;

import java.util.Optional;

public interface GenreCreatePort<T, D> {
    Optional<T> create(D entity) throws CustomException;
}
