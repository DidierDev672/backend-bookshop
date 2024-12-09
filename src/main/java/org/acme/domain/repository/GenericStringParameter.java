package org.acme.domain.repository;

import java.util.Optional;

public interface GenericStringParameter<T> {
    Optional<T> stringParameter(String parameter);
}
