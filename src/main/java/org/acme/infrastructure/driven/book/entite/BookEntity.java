package org.acme.infrastructure.driven.book.entite;

import jakarta.persistence.*;
import lombok.*;
import org.acme.domain.models.Author;
import org.acme.domain.models.DescriptionModel;
import org.acme.domain.models.DetailModel;
import org.acme.domain.models.Editorial;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "g_books")
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {
    @Id
    private UUID uuid = UUID.randomUUID();

    private String title;

    @Column(name = "authors", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<Author> authors;

    @Column(name = "editorial", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private Editorial editorial;

    @Column(name = "detail", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private DetailModel detailModel;

    @Column(name = "description", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private DescriptionModel descriptionModel;

    private String photo;
}