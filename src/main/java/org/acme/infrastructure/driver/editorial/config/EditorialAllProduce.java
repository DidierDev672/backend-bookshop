package org.acme.infrastructure.driver.editorial.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.acme.applications.Editorial.adapters.EditorialAllAdapter;
import org.acme.applications.Editorial.ports.EditorialAllPort;
import org.acme.infrastructure.driven.author.ports.AuthorJpaRepository;
import org.acme.infrastructure.driven.editorial.adapters.EditorialAllPostgresRepository;
import org.acme.infrastructure.driven.editorial.ports.EditorialJpaRepository;

@Slf4j
@ApplicationScoped
public class EditorialAllProduce {
    @Inject
    @Default
    protected EditorialJpaRepository editorialJpaRepository;

    @Inject
    @Default
    protected AuthorJpaRepository authorJpaRepository;

    @Produces
    @Default
    protected EditorialAllPostgresRepository editorialAllPostgresRepository(){
        return EditorialAllPostgresRepository.init(editorialJpaRepository, authorJpaRepository);
    }

    @Produces
    @Default
    protected EditorialAllPort editorialAllPort(){
        return EditorialAllAdapter.init(editorialAllPostgresRepository());
    }
}
