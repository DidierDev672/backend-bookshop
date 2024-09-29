package org.acme.applications.author.ports;

import org.acme.applications.shared.CustomException;
import org.acme.domain.models.Author;

import java.util.Optional;
import java.util.List;

public interface AuthorAllPort {
    Optional<List<Author>> all() throws CustomException;
}
