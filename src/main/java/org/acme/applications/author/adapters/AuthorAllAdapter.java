package org.acme.applications.author.adapters;

import lombok.extern.slf4j.Slf4j;
import org.acme.applications.author.ports.AuthorAllPort;
import org.acme.applications.shared.CustomException;
import org.acme.domain.models.Author;
import org.acme.domain.repository.GenericGetAllRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class AuthorAllAdapter implements AuthorAllPort {
    private final GenericGetAllRepository<Author> authorGenericGetAllRepository;

    private AuthorAllAdapter(GenericGetAllRepository<Author> authorGenericGetAllRepository) {
        this.authorGenericGetAllRepository = authorGenericGetAllRepository;
    }

    public static AuthorAllPort init(GenericGetAllRepository<Author> authorGenericGetAllRepository) {
        return new AuthorAllAdapter(authorGenericGetAllRepository);
    }

    @Override
    public Optional<List<Author>> all() throws CustomException {
        return this.authorGenericGetAllRepository.getAll()
                .map(this::convertToAuthorList);
    }

    private List<Author> convertToAuthorList(List<Author> authors) {
        return authors.stream()
               .map(this::convertToAuthor)
               .collect(Collectors.toList());
    }

    private Author convertToAuthor(Author author){
        return new Author(
                author.getUuid(),
                author.getName(),
                author.getGenre(),
                author.getDescription(),
                author.getPhoto()
        );
    }
}
