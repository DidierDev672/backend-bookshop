package org.acme.infrastructure.driven.user.ports;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.infrastructure.driven.user.entite.UserEntity;

import java.util.UUID;

@ApplicationScoped
public class UserJpaRepository implements PanacheRepositoryBase<UserEntity, UUID> {
    public UserEntity findUser(String username){
        return find("username", username).firstResult();
    }
}
