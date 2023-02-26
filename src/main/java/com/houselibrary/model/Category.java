package com.houselibrary.model;

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
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @OneToMany(mappedBy = "category")
  private List<Subcategory> subcategories;

  public Category(String name) {
    this.name = name;
  }

  public List<Book> getBooks() {
    List<Book> books = new ArrayList<>();
    for (Subcategory subcategory : subcategories) {
      books.addAll(subcategory.getBooks());
    }
    return books;
  }
}
