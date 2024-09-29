package org.acme.infrastructure.driver.genre.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import org.acme.applications.Genre.adapters.GenreCreateAdapter;
import org.acme.applications.Genre.ports.GenreCreatePort;
import org.acme.domain.models.Genre;
import org.acme.infrastructure.driven.genre.adapters.GenreCreatePostgresRepository;
import org.acme.infrastructure.driven.genre.ports.GenreJpaRepository;

@ApplicationScoped
public class GenreCreateProduce {

    @Inject
    @Default
    protected GenreJpaRepository genreJpaRepository;


    @Produces
    @Default
    protected GenreCreatePostgresRepository genreCreatePostgresRepository(){
        return GenreCreatePostgresRepository.init(genreJpaRepository);
    }

    @Produces
    @Default
    protected GenreCreatePort<Genre, Genre> genreGenreGenreCreatePort(){
        return GenreCreateAdapter.init(genreCreatePostgresRepository());
    }
}
