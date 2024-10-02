package org.acme.infrastructure.driven.book.adapters;

import org.acme.domain.models.Author;
import org.acme.domain.models.Book;
import org.acme.domain.models.Editorial;
import org.acme.domain.repository.GenericGetAllRepository;
import org.acme.infrastructure.driven.author.entite.AuthorEntity;
import org.acme.infrastructure.driven.author.ports.AuthorJpaRepository;
import org.acme.infrastructure.driven.book.entite.BookEntity;
import org.acme.infrastructure.driven.book.ports.BookJpaRepository;
import org.acme.infrastructure.driven.editorial.entite.EditorialEntity;
import org.acme.infrastructure.driven.editorial.ports.EditorialJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class BookAllPostgresRepository implements GenericGetAllRepository<Book> {
    private final BookJpaRepository bookJpaRepository;
    private final AuthorJpaRepository authorJpaRepository;
    private final EditorialJpaRepository editorialJpaRepository;

    private BookAllPostgresRepository(BookJpaRepository bookJpaRepository, AuthorJpaRepository authorJpaRepository, EditorialJpaRepository editorialJpaRepository){
        this.bookJpaRepository = bookJpaRepository;
        this.authorJpaRepository = authorJpaRepository;
        this.editorialJpaRepository = editorialJpaRepository;
    }

    public static BookAllPostgresRepository init(BookJpaRepository bookJpaRepository, AuthorJpaRepository authorJpaRepository, EditorialJpaRepository editorialJpaRepository){
        return new BookAllPostgresRepository(bookJpaRepository, authorJpaRepository, editorialJpaRepository);
    }

    @Override
    public Optional<List<Book>> getAll() {
        List<BookEntity> bookEntities = this.bookJpaRepository.listAll();
        return Optional.ofNullable(bookEntities)
                .filter(list -> !list.isEmpty())
                .map(this::mapBookEntitiesToBook);
    }


    private List<Book> mapBookEntitiesToBook(List<BookEntity> bookEntities) {
        return bookEntities.stream()
                .map(this::mapEntityToBook)
                .collect(Collectors.toList());
    }

    private Book mapEntityToBook(BookEntity bookEntity) {
        return new Book(
                bookEntity.getUuid(),
                bookEntity.getTitle(),
                this.mapAuthorEntitiesToAuthor(bookEntity.getAuthors()),
               this.mapEntityToEditorial(this.findEditorialByUuid(bookEntity.getEditorial().getUuid())),
                bookEntity.getDetailModel(),
                bookEntity.getDescriptionModel(),
                bookEntity.getPhoto()
        );
    }

    private EditorialEntity findEditorialByUuid(UUID uuid) {
        return this.editorialJpaRepository.findById(uuid);
    }

    private Editorial mapEntityToEditorial(EditorialEntity editorialEntities) {
        return new Editorial(
                editorialEntities.getUuid(),
                editorialEntities.getName(),
                this.mapAuthorEntitiesToAuthor(editorialEntities.getAuthors()),
                editorialEntities.getBooks(),
                editorialEntities.getKnow(),
                editorialEntities.getPhoto()
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
