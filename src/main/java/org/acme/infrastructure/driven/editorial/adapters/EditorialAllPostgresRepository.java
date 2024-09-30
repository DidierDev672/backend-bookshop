package org.acme.infrastructure.driven.editorial.adapters;

import org.acme.domain.models.Author;
import org.acme.domain.models.Editorial;
import org.acme.domain.repository.GenericGetAllRepository;
import org.acme.infrastructure.driven.author.entite.AuthorEntity;
import org.acme.infrastructure.driven.author.ports.AuthorJpaRepository;
import org.acme.infrastructure.driven.editorial.entite.EditorialEntity;
import org.acme.infrastructure.driven.editorial.ports.EditorialJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class EditorialAllPostgresRepository implements GenericGetAllRepository<Editorial> {
    private final EditorialJpaRepository editorialJpaRepository;
    private final AuthorJpaRepository authorJpaRepository;

    private EditorialAllPostgresRepository(EditorialJpaRepository editorialJpaRepository, AuthorJpaRepository authorJpaRepository){
        this.editorialJpaRepository = editorialJpaRepository;
        this.authorJpaRepository = authorJpaRepository;
    }

    public static EditorialAllPostgresRepository init(EditorialJpaRepository editorialJpaRepository, AuthorJpaRepository authorJpaRepository){
        return new EditorialAllPostgresRepository(editorialJpaRepository, authorJpaRepository);
    }

    @Override
    public Optional<List<Editorial>> getAll() {
        List<EditorialEntity> editorialEntities = this.editorialJpaRepository.listAll();
        return Optional.ofNullable(editorialEntities)
                .filter(list -> !list.isEmpty())
                .map(this::mapEditorialEntitiesToEditorial);
    }

    private List<Editorial> mapEditorialEntitiesToEditorial(List<EditorialEntity> editorialEntities) {
        return editorialEntities.stream()
               .map(this::mapEntityToEditorial)
               .collect(Collectors.toList());
    }

    private Editorial mapEntityToEditorial(EditorialEntity editorialEntity) {
        return new Editorial(
                editorialEntity.getUuid(),
                editorialEntity.getName(),
                this.mapAuthorEntitiesToAuthor(editorialEntity.getAuthors()),
                editorialEntity.getBooks(),
                editorialEntity.getKnow(),
                editorialEntity.getPhoto()
        );
    }

    private List<Author> mapAuthorEntitiesToAuthor(List<Author> authors) {
        return authors.stream()
               .map(author -> this.mapEntityToAuthor(this.findAuthorByUuid(author.getUuid())))
               .collect(Collectors.toList());
    }

    private AuthorEntity findAuthorByUuid(UUID uuid) {
        return this.authorJpaRepository.findById(uuid);
    }

    private Author mapEntityToAuthor(AuthorEntity authorEntity) {
        return new Author(
                authorEntity.getUuid(),
                authorEntity.getName(),
                authorEntity.getGenre(),
                authorEntity.getDescription(),
                authorEntity.getPhoto()
        );
    }
}
