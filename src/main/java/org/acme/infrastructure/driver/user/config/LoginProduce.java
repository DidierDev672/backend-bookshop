package org.acme.infrastructure.driver.user.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.ws.rs.Produces;
import org.acme.applications.user.adapter.LoginAdapter;
import org.acme.applications.user.port.LoginPort;
import org.acme.infrastructure.driven.shared.TokenService;
import org.acme.infrastructure.driven.user.adapters.LoginPostgresRepository;
import org.acme.infrastructure.driven.user.ports.UserJpaRepository;

@ApplicationScoped
public class LoginProduce {
    @Inject
    @Default
    protected UserJpaRepository repository;

    @Inject
    @Default
    protected TokenService tokenService;

    @Produces
    @Default
    protected LoginPostgresRepository loginPostgresRepository(){
        return LoginPostgresRepository.init(repository, tokenService);
    }

    @Produces
    @Default
    protected LoginPort loginPort(){
        return LoginAdapter.init(loginPostgresRepository());
    }
}
