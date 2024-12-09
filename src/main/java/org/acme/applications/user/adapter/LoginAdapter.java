package org.acme.applications.user.adapter;

import org.acme.applications.shared.CustomException;
import org.acme.applications.user.port.LoginPort;
import org.acme.domain.models.user.Login;
import org.acme.domain.repository.GenericTwoParametersRepository;

import java.util.Optional;

public class LoginAdapter implements LoginPort {
    private final GenericTwoParametersRepository<String, String, Login> repository;

    private LoginAdapter(GenericTwoParametersRepository<String, String, Login> repository){
        this.repository = repository;
    }

    public static LoginAdapter init(GenericTwoParametersRepository<String, String, Login> repository){
        return new LoginAdapter(repository);
    }

    @Override
    public Optional<Login> login(String username, String password) throws CustomException {
        this.validateAndCreateLogin(username, password);
        return this.repository.searchTwoParameters(username, password);
    }

    private Login validateAndCreateLogin(String username, String password) {
        if (username == null || username.isEmpty()) {
            throw new CustomException("Username is required.");
        }
        if (password == null || password.isEmpty()) {
            throw new CustomException("Password is required.");
        }
        return Login.create(username, password);
    }
}
