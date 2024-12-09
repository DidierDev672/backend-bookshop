package org.acme.infrastructure.driven.user.adapters;

import org.acme.applications.shared.CustomException;
import org.acme.domain.models.user.Login;
import org.acme.domain.repository.GenericTwoParametersRepository;
import org.acme.infrastructure.driven.shared.TokenService;
import org.acme.infrastructure.driven.user.entite.UserEntity;
import org.acme.infrastructure.driven.user.ports.UserJpaRepository;

import java.util.Optional;

public class LoginPostgresRepository implements GenericTwoParametersRepository<String, String, Login> {
    private final UserJpaRepository repository;
    private final TokenService tokenService;

    private LoginPostgresRepository(UserJpaRepository repository, TokenService tokenService){
        this.repository = repository;
        this.tokenService = tokenService;
    }

    public static LoginPostgresRepository init(UserJpaRepository repository, TokenService tokenService){
        return new LoginPostgresRepository(repository, tokenService);
    }

    @Override
    public Optional<Login> searchTwoParameters(String parameterOne, String parameterTwo) {
        this.validateAndCreateLogin(parameterOne, parameterTwo);
        UserEntity user = this.findUserPostgres(parameterOne);
        if (user!= null) {
            return Optional.of(this.convertToLogin(user));
        }

        return Optional.empty();
    }

    private UserEntity findUserPostgres(String username) {
        UserEntity userUsername = this.repository.findUser(username);
        if (userUsername!= null) {
           String token = this.generateToken(userUsername.getUsername());
           System.out.println("Token: " + token);
        }
        return userUsername;
    }

    private Login convertToLogin(UserEntity entity) {
       return new Login(
               entity.getUsername(),
               entity.getPassword()
       );
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

    private String generateToken(String username) {
        if(username.isEmpty()){
            throw new CustomException("Username cannot be empty");
        }

        return this.tokenService.generateToken(username);
    }
}
