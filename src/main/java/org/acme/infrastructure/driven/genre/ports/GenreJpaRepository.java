package org.acme.infrastructure.driven.genre.ports;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.infrastructure.driven.genre.entite.GenreEntity;

import java.util.UUID;

@ApplicationScoped
public class GenreJpaRepository implements PanacheRepositoryBase<GenreEntity, UUID> {
}
