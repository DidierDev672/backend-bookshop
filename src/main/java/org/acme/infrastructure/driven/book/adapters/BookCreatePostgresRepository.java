package org.acme.infrastructure.driven.book.adapters;

import org.acme.domain.models.Author;
import org.acme.domain.models.Book;
import org.acme.domain.models.Editorial;
import org.acme.domain.repository.GenericCreate;
import org.acme.infrastructure.driven.author.entite.AuthorEntity;
import org.acme.infrastructure.driven.author.ports.AuthorJpaRepository;
import org.acme.infrastructure.driven.book.entite.BookEntity;
import org.acme.infrastructure.driven.book.ports.BookJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookCreatePostgresRepository implements GenericCreate<Book, Book> {
    private final BookJpaRepository bookJpaRepository;

    private BookCreatePostgresRepository(BookJpaRepository bookJpaRepository){
        this.bookJpaRepository = bookJpaRepository;
    }

    public static BookCreatePostgresRepository init(BookJpaRepository bookJpaRepository){
        return new BookCreatePostgresRepository(bookJpaRepository);
    }

    @Override
    public Optional<Book> create(Book entity) {
        if(entity == null){
            return Optional.empty();
        }

        BookEntity bookEntity = this.convertToBookEntity(entity);
        BookEntity persistenceEntity = this.persistEntity(bookEntity);
        return Optional.of(this.convertToBookEntity(persistenceEntity));
    }

    private BookEntity persistEntity(BookEntity bookEntity) {
        this.bookJpaRepository.persist(bookEntity);
        return bookEntity;
    }

    private Book convertToBookEntity(BookEntity bookEntity) {
        return new Book(
                bookEntity.getUuid(),
                bookEntity.getTitle(),
                bookEntity.getAuthors(),
                bookEntity.getEditorial(),
                bookEntity.getDetailModel(),
                bookEntity.getDescriptionModel(),
                bookEntity.getPhoto()
        );
    }


    private BookEntity convertToBookEntity(Book book){
        return new BookEntity(
                book.getUuid(),
                book.getTitle(),
                convertToAuthorList(book.getAuthors()),
                convertToEditorial(book.getEditorial()),
                book.getDetailModel(),
                book.getDescriptionModel(),
                book.getPhoto()
        );
    }


    private Editorial convertToEditorial(Editorial editorial) {
        return new Editorial(
                editorial.getUuid(),
                null,
                null,
                null,
                null,
                null
        );
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
