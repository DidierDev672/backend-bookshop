package org.acme.domain.repository;

import java.util.Optional;

public interface GenericTwoParametersRepository <T, W, S>{
    Optional<S> searchTwoParameters(T parameterOne, W parameterTwo);
}
