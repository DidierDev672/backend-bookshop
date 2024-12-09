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
@Table(name = "g_token")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenEntity {
    @Id
    private UUID uuid = UUID.randomUUID();
    private String username;
    private String token;
}
