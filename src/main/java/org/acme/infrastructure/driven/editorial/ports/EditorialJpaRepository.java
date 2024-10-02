package org.acme.infrastructure.driven.editorial.ports;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.infrastructure.driven.editorial.entite.EditorialEntity;

import java.util.UUID;

@ApplicationScoped
public class EditorialJpaRepository implements PanacheRepositoryBase<EditorialEntity, UUID> {
}
