package org.acme.infrastructure.driven.editorial.adapters;

import org.acme.domain.models.Author;
import org.acme.domain.models.Book;
import org.acme.domain.models.Editorial;
import org.acme.domain.repository.GenericCreate;
import org.acme.infrastructure.driven.editorial.entite.EditorialEntity;
import org.acme.infrastructure.driven.editorial.ports.EditorialJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EditorialCreatePostgresRepository implements GenericCreate<Editorial, Editorial> {
    private final EditorialJpaRepository editorialJpaRepository;

    private EditorialCreatePostgresRepository(EditorialJpaRepository editorialJpaRepository){
        this.editorialJpaRepository = editorialJpaRepository;
    }

    public static EditorialCreatePostgresRepository init(EditorialJpaRepository editorialJpaRepository){
        return new EditorialCreatePostgresRepository(editorialJpaRepository);
    }
    @Override
    public Optional<Editorial> create(Editorial entity) {
        if(entity == null){

            return Optional.empty();
        }

        EditorialEntity editorialEntity = this.convertToEditorialEntity(entity);
        EditorialEntity persistenceEntity = this.persistEntity(editorialEntity);
        return Optional.of(this.convertToEditorialEntity(persistenceEntity));
    }

    private EditorialEntity persistEntity(EditorialEntity editorialEntity){
        this.editorialJpaRepository.persist(editorialEntity);
        return editorialEntity;
    }

    private EditorialEntity convertToEditorialEntity(Editorial editorial) {
        return new EditorialEntity(
                editorial.getUuid(),
                editorial.getName(),
                this.convertToAuthorList(editorial.getAuthors()),
                this.convertToBookList(editorial.getBooks()),
                editorial.getKnow(),
                editorial.getPhoto()
        );
    }

    private Editorial convertToEditorialEntity(EditorialEntity editorialEntity){
        return new Editorial(
                editorialEntity.getUuid(),
                editorialEntity.getName(),
                editorialEntity.getAuthors(),
                editorialEntity.getBooks(),
                editorialEntity.getKnow(),
                editorialEntity.getPhoto()
        );
    }

    private List<Book> convertToBookList(List<Book> books) {
        ArrayList<Book> bookArrayList = new ArrayList<>();
        books.forEach((item) -> {
            Book book1 = new Book(
                    item.getUuid(),
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
            );
            bookArrayList.add(book1);
        });
        return bookArrayList;
    }

    private List<Author> convertToAuthorList(List<Author> authors) {
        ArrayList<Author> authorArrayList = new ArrayList<>();
        authors.forEach((item) -> {
            Author author1 = new Author(
                    item.getUuid(),
                    null,
                    null,
                    null,
                    null
            );
            authorArrayList.add(author1);
        });
        return authorArrayList;
    }
}
