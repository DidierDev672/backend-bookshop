package org.acme.domain.repository;

import java.util.List;
import java.util.Optional;

public interface GenericGetAllRepository<R> {
    Optional<List<R>> getAll();
}
