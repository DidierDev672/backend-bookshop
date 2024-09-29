package org.acme.applications.Genre.adapters;

import org.acme.applications.Genre.ports.GenreCreatePort;
import org.acme.applications.shared.CustomException;
import org.acme.applications.shared.ValidationUtil;
import org.acme.domain.models.Genre;
import org.acme.domain.repository.GenericCreate;
import org.acme.infrastructure.driven.genre.adapters.GenreCreatePostgresRepository;

import java.util.Objects;
import java.util.Optional;

public class GenreCreateAdapter implements GenreCreatePort<Genre, Genre> {
    private final GenericCreate<Genre, Genre> genreGenericCreate;

    private GenreCreateAdapter(GenericCreate<Genre, Genre> genreGenericCreate){
        this.genreGenericCreate = genreGenericCreate;
    }

    public static GenreCreatePort<Genre, Genre> init(GenericCreate<Genre, Genre> genreGenericCreate){
        return new GenreCreateAdapter(genreGenericCreate);
    }

    @Override
    public Optional<Genre> create(Genre entity) throws CustomException {
        this.validateModel(entity);
        return this.genreGenericCreate.create(entity);
    }

    private void validateModel(Genre genre) throws CustomException {
        if(Objects.isNull(genre)) {
            throw new CustomException("Genre cannot be null");
        }
        ValidationUtil.validateField(genre.getName(), "The name of the genus should");
        ValidationUtil.validateString(genre.getName(), "The text string cannot be empty");
    }

}
