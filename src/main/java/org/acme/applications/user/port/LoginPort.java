package org.acme.applications.user.port;

import org.acme.applications.shared.CustomException;
import org.acme.domain.models.user.Login;

import java.util.Optional;

public interface LoginPort {
    Optional<Login> login(String username, String password) throws CustomException;
}
