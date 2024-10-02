package org.acme.infrastructure.driver.editorial.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import org.acme.applications.Editorial.adapters.EditorialCreateAdapter;
import org.acme.applications.Editorial.ports.EditorialCreatePort;
import org.acme.domain.models.Editorial;
import org.acme.infrastructure.driven.editorial.adapters.EditorialCreatePostgresRepository;
import org.acme.infrastructure.driven.editorial.ports.EditorialJpaRepository;

@ApplicationScoped
public class EditorialCreateProduce {
    @Inject
    @Default
    protected EditorialJpaRepository editorialJpaRepository;

    @Produces
    @Default
    protected EditorialCreatePostgresRepository editorialCreatePostgresRepository(){
        return EditorialCreatePostgresRepository.init(editorialJpaRepository);
    }

    @Produces
    @Default
    protected EditorialCreatePort<Editorial, Editorial> editorialEditorialEditorialCreatePort(){
        return EditorialCreateAdapter.init(editorialCreatePostgresRepository());
    }
}
