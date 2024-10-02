package org.acme.infrastructure.driver.book.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import org.acme.applications.Book.adapters.BookCreateAdapter;
import org.acme.applications.Book.ports.BookCreatePort;
import org.acme.domain.models.Book;
import org.acme.infrastructure.driven.book.adapters.BookCreatePostgresRepository;
import org.acme.infrastructure.driven.book.ports.BookJpaRepository;

@ApplicationScoped
public class BookCreateProduce {
    @Inject
    @Default
    protected BookJpaRepository bookJpaRepository;

    @Produces
    @Default
    protected BookCreatePostgresRepository bookCreatePostgresRepository(){
        return BookCreatePostgresRepository.init(bookJpaRepository);
    }

    @Produces
    @Default
    protected BookCreatePort<Book, Book> bookBookBookCreatePort(){
        return BookCreateAdapter.init(bookCreatePostgresRepository());
    }
}
