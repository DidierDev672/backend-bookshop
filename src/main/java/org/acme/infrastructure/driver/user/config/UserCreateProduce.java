package org.acme.infrastructure.driver.user.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import org.acme.applications.user.adapter.UserCreateAdapter;
import org.acme.applications.user.port.UserCreatePort;
import org.acme.domain.models.user.UserModel;
import org.acme.infrastructure.driven.user.adapters.UserCreatePostgresRepository;
import org.acme.infrastructure.driven.user.ports.UserJpaRepository;

@ApplicationScoped
public class UserCreateProduce {
    @Inject
    @Default
    protected UserJpaRepository repository;

    @Produces
    @Default
    protected UserCreatePostgresRepository repository(){
        return UserCreatePostgresRepository.init(repository);
    }

    @Produces
    @Default
    protected UserCreatePort<UserModel> userModelUserCreatePort(){
        return UserCreateAdapter.init(repository());
    }
}
