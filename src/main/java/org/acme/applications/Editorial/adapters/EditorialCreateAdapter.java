package org.acme.applications.Editorial.adapters;

import org.acme.applications.Editorial.ports.EditorialCreatePort;
import org.acme.applications.shared.CustomException;
import org.acme.applications.shared.ValidationUtil;
import org.acme.domain.models.Editorial;
import org.acme.domain.repository.GenericCreate;

import java.util.Objects;
import java.util.Optional;

public class EditorialCreateAdapter implements EditorialCreatePort<Editorial, Editorial> {
    private final GenericCreate<Editorial, Editorial> create;

    private EditorialCreateAdapter(GenericCreate<Editorial, Editorial> create){
        this.create = create;
    }

    public static EditorialCreatePort<Editorial, Editorial> init(GenericCreate<Editorial, Editorial> create){
        return new EditorialCreateAdapter(create);
    }
    @Override
    public Optional<Editorial> create(Editorial entity) throws CustomException {
        this.validateModel(entity);
        return this.create.create(entity);
    }

    private void validateModel(Editorial editorial) throws CustomException {
        if(Objects.isNull(editorial)){
            throw new CustomException("Editorial cannot be null");
        }

        ValidationUtil.validateField(editorial.getName(), "The name of the publisher cannot be null");
        ValidationUtil.validateString(editorial.getName(), "The name of the publisher must not be clean");
        ValidationUtil.validateField(editorial.getAuthors(), "The list of authors cannot be null");
        ValidationUtil.validateField(editorial.getBooks(), "The list of books cannot be null");
        ValidationUtil.validateField(editorial.getKnow(), "The know of the publisher cannot be null");
        ValidationUtil.validateField(editorial.getPhoto(), "The photo of the publisher cannot be null");
        ValidationUtil.validateString(editorial.getPhoto(), "The photo of the publisher must not be clean");
    }
}
