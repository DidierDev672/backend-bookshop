package org.acme.applications.Genre.ports;

import org.acme.applications.shared.CustomException;
import org.acme.domain.models.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreAllPort {
    Optional<List<Genre>> all() throws CustomException;
}
