package com.houselibrary.core.model;

import com.houselibrary.core.template.Model;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Category extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<Subcategory> subcategories;

    @Builder
    public Category(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        if(subcategories == null)
            return null;
        subcategories.forEach(subcategory ->
                books.addAll(subcategory.getBooks()));
        return books;
    }

}
