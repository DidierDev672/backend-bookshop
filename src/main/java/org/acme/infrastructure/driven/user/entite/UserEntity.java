package org.acme.infrastructure.driven.user.entite;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "g_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    private UUID uuid = UUID.randomUUID();

    private String name_full;
    private String phone;
    private String email;
    private String photo;
    private String username;
    private String password;
    private String role;
}
