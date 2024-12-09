package org.acme.infrastructure.driven.user.adapters;

import lombok.extern.slf4j.Slf4j;
import org.acme.applications.shared.CustomException;
import org.acme.domain.models.user.Token;
import org.acme.domain.repository.GenericStringParameter;
import org.acme.infrastructure.driven.shared.TokenGenerationException;
import org.acme.infrastructure.driven.shared.TokenService;
import org.acme.infrastructure.driven.shared.TokenStorageException;
import org.acme.infrastructure.driven.user.ports.TokenJpaRepository;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Slf4j
public class TokenPostgresRepository implements GenericStringParameter<Token> {
    private final TokenJpaRepository repository;
    private final TokenService tokenService;

    private TokenPostgresRepository (TokenJpaRepository repository, TokenService tokenService) {
        this.repository = repository;
        this.tokenService = tokenService;
    };

    public static TokenPostgresRepository init(TokenJpaRepository repository, TokenService tokenService) {
        return new TokenPostgresRepository(repository, tokenService);
    }

    @Override
    public Optional<Token> stringParameter(String parameter) {
        //! Validacion de entrada mas robusta.
        validateInput(parameter);

        try {
            //* Generar token con manejo de errores.
           String token = generateTokenSafely(parameter);

           //* Almacenar token de manera segura
            saveTokenSafely(parameter, token);

            return Optional.of(createTokenObject(token, parameter));
        }catch (TokenGenerationException | TokenStorageException e) {
            //? Logging centralizado.
            log.error("Error processing token for user: {}", parameter, e);
            return Optional.empty();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }


    //! Validacion de entrada centralizada.
    private void validateInput(String input){
        Objects.requireNonNull(input, "Input parameter cannot be null");

        //? Validaciones adicionales si es necesario.
        if(input.trim().isEmpty()){
            throw new IllegalArgumentException("Input parameter cannot be empty");
        }
    }

    //! Generacion de token con manejo de errores
    private String generateTokenSafely(String username) {
        try {
            String token = tokenService.generateToken(username);

            //? Validaciones adicionales del token generado
            if(token == null || token.trim().isEmpty()){
                throw new TokenGenerationException("Generated token is null or empty");
            }

            return token;
        } catch (Exception e) {
            throw new TokenGenerationException("Failed to generate token", e);
        }
    }

    //! Almacenamiento de token seguro
    private void saveTokenSafely(String key, String token) {
        try {
           Objects.requireNonNull(key, "key must not be null");
           Objects.requireNonNull(token, "token must not be null");

           //? Incrementar de manera segura.
            repository.increment(token, 1);
        } catch(Exception e){
            throw new TokenStorageException("Failed to save token", e);
        }
    }

    //! Creacion de objeto Token mas robusto.
    private Token createTokenObject(String token, String username) throws IllegalAccessException {
        Objects.requireNonNull(username, "username cannot be null");
        Objects.requireNonNull(token, "token cannot be null");

        return new Token(
                UUID.randomUUID(),
                sanitizeUsername(username),
                sanitizeToken(token)
        );
    }

    //! Metodo de sanitizacion
    private String sanitizeUsername(String username){
        //!  Eliminar caracteres especiales, limitar longitud, etc.
        return username.replaceAll("[^a-zA-Z0-9]", "")
                .toLowerCase()
                .trim();
    }

    private String sanitizeToken(String token) throws IllegalAccessException {
        Objects.requireNonNull(token, "Token cannot be null");

        int MAX_TOKEN_LENGTH = 2048;

        //! Validar logitud, formato etc.
        if(token.length() < 10 ||  token.length() > MAX_TOKEN_LENGTH){
            throw new IllegalArgumentException("Token length out of acceptable range");
        }

        if(!isValidJwtFormat(token)){
            throw new IllegalAccessException("Invalid JWT token token format");
        }

        return token;
    }

    private boolean isValidJwtFormat(String token) {
        //! Validar estructura basica de JWT (3 partes seperadas por puntos)

        return token != null &&
                token.split("\\.").length == 3 &&
                !token.isEmpty();
    }
}
