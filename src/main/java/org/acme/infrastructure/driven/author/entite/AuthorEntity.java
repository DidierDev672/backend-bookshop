package org.acme.infrastructure.driven.author.entite;

import jakarta.persistence.*;
import lombok.*;
import org.acme.domain.models.Genre;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "g_author")
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    private String name;

    @Column(name = "genre", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<Genre> genre;

    @Column(name = "description", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> description;

    private String photo;
}
