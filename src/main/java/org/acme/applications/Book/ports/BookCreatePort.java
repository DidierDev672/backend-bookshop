package org.acme.applications.Book.ports;

import org.acme.applications.shared.CustomException;

import java.util.Optional;

public interface BookCreatePort<T, R> {
    Optional<T> create(R entities) throws CustomException;
}
