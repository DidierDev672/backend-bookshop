package org.acme.applications.user.adapter;

import org.acme.applications.shared.CustomException;
import org.acme.applications.user.port.TokenPort;
import org.acme.domain.models.user.Token;
import org.acme.domain.repository.GenericCreate;
import org.acme.domain.repository.GenericStringParameter;

import java.util.Objects;
import java.util.Optional;

public class TokenAdapter implements TokenPort<Token> {
    private final GenericStringParameter<Token> repository;

    private TokenAdapter(GenericStringParameter<Token> repository) {
        this.repository = repository;
    }

    public static TokenAdapter init(GenericStringParameter<Token> repository) {
        return new TokenAdapter(repository);
    }

    @Override
    public Optional<Token> createToken(String username) throws CustomException {
        if(username == null){
            throw new CustomException("Username cannot be null");
        }
        return this.repository.stringParameter(username);
    }


}
