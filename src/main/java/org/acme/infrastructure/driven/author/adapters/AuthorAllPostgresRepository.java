package org.acme.infrastructure.driven.author.adapters;

import org.acme.domain.models.Author;
import org.acme.domain.models.Genre;
import org.acme.domain.repository.GenericGetAllRepository;
import org.acme.infrastructure.driven.author.entite.AuthorEntity;
import org.acme.infrastructure.driven.author.ports.AuthorJpaRepository;
import org.acme.infrastructure.driven.genre.entite.GenreEntity;
import org.acme.infrastructure.driven.genre.ports.GenreJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class AuthorAllPostgresRepository implements GenericGetAllRepository<Author> {
    private final AuthorJpaRepository authorJpaRepository;
    private final GenreJpaRepository genreJpaRepository;

    private AuthorAllPostgresRepository(AuthorJpaRepository authorJpaRepository, GenreJpaRepository genreJpaRepository){
        this.authorJpaRepository = authorJpaRepository;
        this.genreJpaRepository = genreJpaRepository;
    }

    public static AuthorAllPostgresRepository init(AuthorJpaRepository authorJpaRepository, GenreJpaRepository genreJpaRepository){
        return new AuthorAllPostgresRepository(authorJpaRepository, genreJpaRepository);
    }

    @Override
    public Optional<List<Author>> getAll() {
        List<AuthorEntity> authorEntities = this.authorJpaRepository.listAll();
        return Optional.ofNullable(authorEntities)
                .filter(list -> !list.isEmpty())
                .map(this::mapAuthorEntitiesToAuthor);
    }

    private List<Author> mapAuthorEntitiesToAuthor(List<AuthorEntity> authorEntities) {
        return authorEntities.stream()
                .map(this::mapEntityToAuthor)
                .collect(Collectors.toList());
    }

    private Author mapEntityToAuthor(AuthorEntity authorEntity) {
        return new Author(
                authorEntity.getUuid(),
                authorEntity.getName(),
                this.mapGenreEntitiesToGenres(authorEntity.getGenre()),
                authorEntity.getDescription(),
                authorEntity.getPhoto()
        );
    }

    private List<Genre> mapGenreEntitiesToGenres(List<Genre> genres) {
        return genres.stream()
                .map(genre -> this.mapEntityToGenre(this.findGenreByUuid(genre.getUuid())))
                .collect(Collectors.toList());
    }

    private GenreEntity findGenreByUuid(UUID uuid) {
        return this.genreJpaRepository.findById(uuid);
    }

    private Genre mapEntityToGenre(GenreEntity genreEntity) {
        return new Genre(
                genreEntity.getUuid(),
                genreEntity.getName()
        );
    }
}
