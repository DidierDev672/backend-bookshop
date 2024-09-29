package org.acme.infrastructure.driven.author.adapters;

import lombok.extern.slf4j.Slf4j;
import org.acme.domain.models.Author;
import org.acme.domain.models.Genre;
import org.acme.domain.repository.GenericCreate;
import org.acme.infrastructure.driven.author.entite.AuthorEntity;
import org.acme.infrastructure.driven.author.ports.AuthorJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthorCreatePostgresRepository implements GenericCreate<Author, Author> {
    private final AuthorJpaRepository authorJpaRepository;

    private AuthorCreatePostgresRepository(AuthorJpaRepository authorJpaRepository){
        this.authorJpaRepository = authorJpaRepository;
    }

    public static AuthorCreatePostgresRepository init(AuthorJpaRepository authorJpaRepository){
        return new AuthorCreatePostgresRepository(authorJpaRepository);
    }


    @Override
    public Optional<Author> create(Author entity) {
        if(entity == null){
            return Optional.empty();
        }

        AuthorEntity authorEntity = this.convertToAuthorEntity(entity);
        AuthorEntity persistenceEntity = this.persistEntity(authorEntity);
        return Optional.of(this.convertToAuthorEntity(persistenceEntity));
    }

    private AuthorEntity persistEntity(AuthorEntity authorEntity) {
        this.authorJpaRepository.persist(authorEntity);
        return authorEntity;
    }

    private AuthorEntity convertToAuthorEntity(Author author) {
        return new AuthorEntity(
                author.getUuid(),
                author.getName(),
                this.convertToGenre(author.getGenre()),
                author.getDescription(),
                author.getPhoto()
        );
    }

    private Author convertToAuthorEntity(AuthorEntity author) {
        return new Author(
                author.getUuid(),
                author.getName(),
                this.convertToGenre(author.getGenre()),
                author.getDescription(),
                author.getPhoto()
        );
    }

    private List<Genre> convertToGenre(List<Genre> genre) {
        ArrayList<Genre> genres = new ArrayList<>();
        genre.forEach((item) -> {
             Genre genre1 = new Genre(item.getUuid(), null);
            genres.add(genre1);
        });
        return genres;
    }
}
