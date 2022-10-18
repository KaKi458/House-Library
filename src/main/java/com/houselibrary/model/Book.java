package com.houselibrary.model;

import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String title;

    public Book(String title, Author author, Category category, Subcategory subcategory, String publisher) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.subcategory = subcategory;
        this.publisher = publisher;
    }

    public Book(String title, Category category, Subcategory subcategory) {
        this.title = title;
        this.category = category;
        this.subcategory = subcategory;
    }

    @Column
    private Author author;

    @ManyToOne
    @JoinColumn(name="category_id", nullable=false)
    private Category category;

    @ManyToOne
    @JoinColumn(name="subcategory_id", nullable=false)
    private Subcategory subcategory;

    @Column
    private String publisher;
}
