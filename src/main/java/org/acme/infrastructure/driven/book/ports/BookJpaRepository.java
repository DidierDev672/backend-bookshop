package org.acme.infrastructure.driven.book.ports;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.infrastructure.driven.book.entite.BookEntity;

import java.util.UUID;

@ApplicationScoped
public class BookJpaRepository implements PanacheRepositoryBase<BookEntity, UUID> {
}
