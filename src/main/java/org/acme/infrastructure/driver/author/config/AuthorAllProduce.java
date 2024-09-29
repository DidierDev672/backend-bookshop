package org.acme.infrastructure.driver.author.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import org.acme.applications.author.adapters.AuthorAllAdapter;
import org.acme.applications.author.ports.AuthorAllPort;
import org.acme.infrastructure.driven.author.adapters.AuthorAllPostgresRepository;
import org.acme.infrastructure.driven.author.ports.AuthorJpaRepository;
import org.acme.infrastructure.driven.genre.ports.GenreJpaRepository;

@ApplicationScoped
public class AuthorAllProduce {
    @Inject
    @Default
    protected AuthorJpaRepository authorJpaRepository;

    @Inject
    @Default
    protected GenreJpaRepository genreJpaRepository;


    @Produces
    @Default
    protected AuthorAllPostgresRepository authorAllPostgresRepository() {
        return AuthorAllPostgresRepository.init(authorJpaRepository, genreJpaRepository);
    }

    @Produces
    @Default
    protected AuthorAllPort authorAllPort() {
        return AuthorAllAdapter.init(authorAllPostgresRepository());
    }
}
