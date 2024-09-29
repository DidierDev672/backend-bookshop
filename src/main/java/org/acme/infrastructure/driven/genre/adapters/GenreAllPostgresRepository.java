package org.acme.infrastructure.driven.genre.adapters;

import lombok.extern.slf4j.Slf4j;
import org.acme.domain.models.Genre;
import org.acme.domain.repository.GenericGetAllRepository;
import org.acme.infrastructure.driven.genre.entite.GenreEntity;
import org.acme.infrastructure.driven.genre.ports.GenreJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class GenreAllPostgresRepository implements GenericGetAllRepository<Genre> {
    private final GenreJpaRepository genreJpaRepository;

    private GenreAllPostgresRepository(GenreJpaRepository genreJpaRepository){
        this.genreJpaRepository = genreJpaRepository;
    }

    public static GenreAllPostgresRepository init(GenreJpaRepository genreJpaRepository){
        return new GenreAllPostgresRepository(genreJpaRepository);
    }

    @Override
    public Optional<List<Genre>> getAll() {
        List<GenreEntity> genres = this.genreJpaRepository.listAll();
        return Optional.ofNullable(genres)
                .filter(list -> !list.isEmpty())
                .map(this::convertToListGenre);
    }


    private List<Genre> convertToListGenre(List<GenreEntity> genres) {
        return genres.stream()
                .map(this::convertToGenre)
                .collect(Collectors.toList());
    }

    private Genre convertToGenre(GenreEntity genreEntity) {
        return new Genre(
                genreEntity.getUuid(),
                genreEntity.getName()
        );
    }
}
