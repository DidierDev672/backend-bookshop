package org.acme.applications.Editorial.ports;

import org.acme.applications.shared.CustomException;

import java.util.Optional;

public interface EditorialCreatePort<T, R> {
    Optional<T> create(R entity) throws CustomException;
}
