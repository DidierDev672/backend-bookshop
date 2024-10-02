package org.acme.applications.Book.ports;

import org.acme.applications.shared.CustomException;
import org.acme.domain.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookAllPort {
    Optional<List<Book>> all() throws CustomException;
}
