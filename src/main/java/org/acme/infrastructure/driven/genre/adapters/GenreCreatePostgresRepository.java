package org.acme.infrastructure.driven.genre.adapters;

import org.acme.domain.models.Genre;
import org.acme.domain.repository.GenericCreate;
import org.acme.infrastructure.driven.genre.entite.GenreEntity;
import org.acme.infrastructure.driven.genre.ports.GenreJpaRepository;

import java.util.Optional;

public class GenreCreatePostgresRepository implements GenericCreate<Genre, Genre> {
    private final GenreJpaRepository genreJpaRepository;

    private GenreCreatePostgresRepository(GenreJpaRepository genreJpaRepository){
        this.genreJpaRepository = genreJpaRepository;
    }

    public static GenreCreatePostgresRepository init(GenreJpaRepository genreJpaRepository){
        return new GenreCreatePostgresRepository(genreJpaRepository);
    }
    @Override
    public Optional<Genre> create(Genre entity) {
        if(entity == null){
            return Optional.empty();
        }

        GenreEntity entityGenre = this.convertToGenre(entity);
        GenreEntity persistenceEntity = this.persistEntity(entityGenre);
        return Optional.of(this.convertToGenre(persistenceEntity));
    }

    private GenreEntity persistEntity(GenreEntity genreEntity) {
        this.genreJpaRepository.persist(genreEntity);
        return genreEntity;
    }

    private GenreEntity convertToGenre(Genre genre){
        return new GenreEntity(
                genre.getUuid(),
                genre.getName()
        );
    }

    private Genre convertToGenre(GenreEntity genreEntity){
        return new Genre(
                genreEntity.getUuid(),
                genreEntity.getName()
        );
    }
}
