package org.acme.domain.repository;

import java.util.Optional;

public interface GenericCreate <T, D>{
    Optional<T> create(D entity);
}
