package org.acme.applications.author.adapters;

import lombok.extern.slf4j.Slf4j;
import org.acme.applications.author.ports.AuthorCreatePort;
import org.acme.applications.shared.CustomException;
import org.acme.applications.shared.ValidationUtil;
import org.acme.domain.models.Author;
import org.acme.domain.repository.GenericCreate;

import java.util.Objects;
import java.util.Optional;

public class AuthorCreateAdapter implements AuthorCreatePort<Author, Author> {
    private final GenericCreate<Author, Author> create;

    private AuthorCreateAdapter(GenericCreate<Author, Author> create){
        this.create = create;
    }

    public static AuthorCreatePort<Author, Author> init(GenericCreate<Author, Author> create){
        return new AuthorCreateAdapter(create);
    }

    @Override
    public Optional<Author> create(Author entity) throws CustomException {
        this.validateModel(entity);
        return this.create.create(entity);
    }

    private void validateModel(Author author) throws CustomException {
        if(Objects.isNull(author)){
            throw new CustomException("Author cannot be null");
        }

        ValidationUtil.validateField(author.getName(), "The author name should not be null");
        ValidationUtil.validateString(author.getName(), "The text string cannot be empty");
        ValidationUtil.validateField(author.getGenre(), "The literary genre should not be null");
        ValidationUtil.validateField(author.getDescription(), "The author description should not be null");
    }
}
