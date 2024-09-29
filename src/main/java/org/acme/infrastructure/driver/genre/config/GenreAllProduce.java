package org.acme.infrastructure.driver.genre.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import org.acme.applications.Genre.adapters.GenreAllAdapter;
import org.acme.applications.Genre.ports.GenreAllPort;
import org.acme.infrastructure.driven.genre.adapters.GenreAllPostgresRepository;
import org.acme.infrastructure.driven.genre.ports.GenreJpaRepository;


@ApplicationScoped
public class GenreAllProduce {
    @Inject
    @Default
    protected GenreJpaRepository genreJpaRepository;

    @Produces
    @Default
    protected GenreAllPostgresRepository genreAllPostgresRepository(){
        return GenreAllPostgresRepository.init(genreJpaRepository);
    }

    @Produces
    @Default
    protected GenreAllPort genreAllPort(){
        return GenreAllAdapter.init(genreAllPostgresRepository());
    }
}
