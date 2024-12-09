package org.acme.infrastructure.driven.user.adapters;

import org.acme.domain.models.user.UserModel;
import org.acme.domain.repository.GenericCreate;
import org.acme.infrastructure.driven.shared.PasswordUtils;
import org.acme.infrastructure.driven.user.entite.UserEntity;
import org.acme.infrastructure.driven.user.ports.UserJpaRepository;

import java.util.Optional;

public class UserCreatePostgresRepository implements GenericCreate<UserModel, UserModel> {
    private final UserJpaRepository repository;

    private UserCreatePostgresRepository(UserJpaRepository repository){
        this.repository = repository;
    }

    public static UserCreatePostgresRepository init(UserJpaRepository repository){
        return new UserCreatePostgresRepository(repository);
    }

    @Override
    public Optional<UserModel> create(UserModel entity) {
        if(entity == null){
            return Optional.empty();
        }

        UserEntity dataUserEntity = this.convertToUserEntity(entity);
        UserEntity persistedEntity = this.persistEntity(dataUserEntity);
        return Optional.of(this.convertToUserModel(persistedEntity));
    }

    private UserModel convertToUserModel(UserEntity entity) {
        return new UserModel(
                entity.getUuid(),
                entity.getName_full(),
                entity.getPhone(),
                entity.getEmail(),
                entity.getPhoto(),
                entity.getUsername(),
                entity.getPassword(),  // Hashed password.
                entity.getRole()
        );
    }

    private UserEntity persistEntity(UserEntity entity){
        this.repository.persistAndFlush(entity);
        return entity;
    }

    private UserEntity convertToUserEntity(UserModel model){
        String hashedPassword = PasswordUtils.hashPassword(model.getPassword());
        return new UserEntity(
                model.getUuid(),
                model.getName_full(),
                model.getPhone(),
                model.getEmail(),
                model.getPhoto(),
                model.getUsername(),
                hashedPassword,
                model.getRole()
        );
    }
}
