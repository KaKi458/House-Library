package com.houselibrary.model;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


import javax.persistence.*;
import java.util.List;

@Entity
@SuperBuilder
@NoArgsConstructor
public class Subcategory {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="category_id", nullable=false)
    private Category parent;

    @OneToMany(mappedBy="subcategory")
    private List<Book> books;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return "Subcategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parent=" + parent +
                ", books=" + books +
                '}';
    }
}
