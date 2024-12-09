package org.acme.infrastructure.driver.user.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.ws.rs.Produces;
import org.acme.applications.user.adapter.TokenAdapter;
import org.acme.applications.user.port.TokenPort;
import org.acme.domain.models.user.Token;
import org.acme.infrastructure.driven.shared.TokenService;
import org.acme.infrastructure.driven.user.adapters.TokenPostgresRepository;
import org.acme.infrastructure.driven.user.ports.TokenJpaRepository;

@ApplicationScoped
public class TokenProduce {
    @Inject
    @Default
    protected TokenJpaRepository repository;

    @Inject
    @Default
    protected TokenService tokenService;

    @Produces
    @Default
    protected TokenPostgresRepository repository(){
        return TokenPostgresRepository.init(repository, tokenService);
    }

    @Produces
    @Default
    protected TokenPort<Token> tokenTokenPort(){
        return TokenAdapter.init(repository());
    }
}
