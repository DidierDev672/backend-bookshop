package org.acme.applications.Editorial.adapters;

import lombok.extern.slf4j.Slf4j;
import org.acme.applications.Editorial.ports.EditorialAllPort;
import org.acme.applications.shared.CustomException;
import org.acme.domain.models.Editorial;
import org.acme.domain.repository.GenericGetAllRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class EditorialAllAdapter implements EditorialAllPort {
    private final GenericGetAllRepository<Editorial> editorialGenericGetAllRepository;
    private EditorialAllAdapter (GenericGetAllRepository<Editorial> editorialGenericGetAllRepository){
        this.editorialGenericGetAllRepository = editorialGenericGetAllRepository;
    }

    public static EditorialAllAdapter init(GenericGetAllRepository<Editorial> editorialGenericGetAllRepository){
        return new EditorialAllAdapter(editorialGenericGetAllRepository);
    }
    @Override
    public Optional<List<Editorial>> all() throws CustomException {
        return this.editorialGenericGetAllRepository.getAll()
                .map(this::convertToEditorialList);
    }

    private List<Editorial> convertToEditorialList(List<Editorial> editorials) {
        return editorials.stream()
               .map(this::convertToEditorial)
               .collect(Collectors.toList());
    }

    private Editorial convertToEditorial(Editorial editorial) {
        return new Editorial(
                editorial.getUuid(),
                editorial.getName(),
                editorial.getAuthors(),
                editorial.getBooks(),
                editorial.getKnow(),
                editorial.getPhoto()
        );
    }
}
