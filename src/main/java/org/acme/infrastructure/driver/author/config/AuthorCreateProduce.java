package org.acme.infrastructure.driver.author.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.acme.applications.author.adapters.AuthorCreateAdapter;
import org.acme.applications.author.ports.AuthorCreatePort;
import org.acme.domain.models.Author;
import org.acme.infrastructure.driven.author.adapters.AuthorCreatePostgresRepository;
import org.acme.infrastructure.driven.author.ports.AuthorJpaRepository;

@Slf4j
@ApplicationScoped
public class AuthorCreateProduce {

    @Inject
    @Default
    protected AuthorJpaRepository authorJpaRepository;

    @Produces
    @Default
    protected AuthorCreatePostgresRepository authorCreatePostgresRepository() {
        return AuthorCreatePostgresRepository.init(authorJpaRepository);
    }

    @Produces
    @Default
    protected AuthorCreatePort<Author, Author> authorAuthorAuthorCreatePort(){
        return AuthorCreateAdapter.init(authorCreatePostgresRepository());
    }

}
