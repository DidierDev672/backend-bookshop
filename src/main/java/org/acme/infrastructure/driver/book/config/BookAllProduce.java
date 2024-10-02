package org.acme.infrastructure.driver.book.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import org.acme.applications.Book.adapters.BookAllAdapter;
import org.acme.applications.Book.ports.BookAllPort;
import org.acme.infrastructure.driven.author.ports.AuthorJpaRepository;
import org.acme.infrastructure.driven.book.adapters.BookAllPostgresRepository;
import org.acme.infrastructure.driven.book.ports.BookJpaRepository;
import org.acme.infrastructure.driven.editorial.ports.EditorialJpaRepository;

@ApplicationScoped
public class BookAllProduce {

    @Inject
    @Default
    protected BookJpaRepository bookJpaRepository;

    @Inject
    @Default
    protected AuthorJpaRepository authorJpaRepository;

    @Inject
    @Default
    protected EditorialJpaRepository editorialJpaRepository;

    @Produces
    @Default
    protected BookAllPostgresRepository bookAllPostgresRepository(){
        return BookAllPostgresRepository.init(bookJpaRepository, authorJpaRepository, editorialJpaRepository);
    }

    @Produces
    @Default
    protected BookAllPort bookAllPort(){
        return BookAllAdapter.init(bookAllPostgresRepository());
    }
}
