package org.acme.infrastructure.driven.editorial.entite;

import jakarta.persistence.*;
import lombok.*;
import org.acme.domain.models.Author;
import org.acme.domain.models.Book;
import org.acme.domain.models.Know;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "g_editorial")
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditorialEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    private String name;

    @Column(name = "authors", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<Author> authors;

    @Column(name = "books", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<Book> books;

    @Column(name = "know", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private Know know;

    private String photo;
}
