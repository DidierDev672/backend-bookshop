package org.acme.applications.Editorial.ports;

import org.acme.applications.shared.CustomException;
import org.acme.domain.models.Editorial;

import java.util.List;
import java.util.Optional;

public interface EditorialAllPort {
    Optional<List<Editorial>> all() throws CustomException;
}
