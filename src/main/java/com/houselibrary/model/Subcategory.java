package com.houselibrary.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Subcategory {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name="category_id", nullable=false)
    private Category parent;

    @OneToMany(mappedBy="subcategory")
    private Set<Book> books;

    public Subcategory(String name) {
        this.name = name;
    }

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

    public Set<Book> getBooks() {
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
