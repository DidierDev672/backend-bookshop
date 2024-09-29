package org.acme.infrastructure.driven.author.ports;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.infrastructure.driven.author.entite.AuthorEntity;

import java.util.UUID;

@ApplicationScoped
public class AuthorJpaRepository implements PanacheRepositoryBase<AuthorEntity, UUID> {
}
