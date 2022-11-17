package com.houselibrary.core.model;

import com.houselibrary.core.template.Model;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Book extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @ManyToMany(mappedBy = "books")
    private List<Author> authors;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subcategory_id", nullable = false)
    private Subcategory subcategory;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Builder
    public Book(
            String title,
            Category category,
            Subcategory subcategory,
            Priority priority,
            List<Author> authors) {
        this.title = title;
        this.category = category;
        this.subcategory = subcategory;
        this.priority = priority;
        this.authors = authors;
    }
}

