package org.acme.applications.Genre.adapters;

import org.acme.applications.Genre.ports.GenreAllPort;
import org.acme.applications.shared.CustomException;
import org.acme.domain.models.Genre;
import org.acme.domain.repository.GenericGetAllRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class GenreAllAdapter implements GenreAllPort {
    private final GenericGetAllRepository<Genre> genreGenericGetAllRepository;

    private GenreAllAdapter(GenericGetAllRepository<Genre> genreGenericGetAllRepository){
        this.genreGenericGetAllRepository = genreGenericGetAllRepository;
    }

    public static GenreAllPort init(GenericGetAllRepository<Genre> genreGenericGetAllRepository){
        return new GenreAllAdapter(genreGenericGetAllRepository);
    }

    @Override
    public Optional<List<Genre>> all() throws CustomException {
        return this.genreGenericGetAllRepository.getAll()
                .map(this::convertToGenreList);
    }

    private List<Genre> convertToGenreList(List<Genre> genres) {
        return genres.stream()
               .map(this::converToGenre)
               .collect(Collectors.toList());
    }

    private Genre converToGenre(Genre genre){
         return new Genre(
                 genre.getUuid(),
                 genre.getName()
         );
    }
}
