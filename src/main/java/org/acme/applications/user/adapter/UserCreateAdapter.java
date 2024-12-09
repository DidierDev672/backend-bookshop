package org.acme.applications.user.adapter;

import org.acme.applications.shared.CustomException;
import org.acme.applications.user.port.UserCreatePort;
import org.acme.domain.models.user.UserModel;
import org.acme.domain.repository.GenericCreate;

import java.util.Optional;

public class UserCreateAdapter implements UserCreatePort<UserModel> {
    private final GenericCreate<UserModel, UserModel> create;

    private UserCreateAdapter(GenericCreate<UserModel, UserModel> create){
        this.create = create;
    }

    public static UserCreateAdapter init(GenericCreate<UserModel, UserModel> create){
        return new UserCreateAdapter(create);
    }

    @Override
    public Optional<UserModel> createUser(UserModel entity) throws CustomException {
        this.validateModel(entity);
        return this.create.create(entity);
    }

    private void validateModel(UserModel user) throws CustomException {
        if(user == null){
            throw new CustomException("User cannot be null");
        }
        if(user.getName_full() == null || user.getName_full().isEmpty()){
            throw new CustomException("Name must be provided");
        }
        if(user.getPhone() == null || user.getPhone().isEmpty()){
            throw new CustomException("Phone must be provided");
        }
        if(user.getEmail() == null || user.getEmail().isEmpty()){
            throw new CustomException("Email must be provided");
        }
        if(user.getPhoto() == null || user.getPhoto().isEmpty()){
            throw new CustomException("Photo must be provided");
        }
        if(user.getRole() == null || user.getRole().isEmpty()){
            throw new CustomException("Role must be provided");
        }
    }
}
