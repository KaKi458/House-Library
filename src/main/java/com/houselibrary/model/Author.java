package com.houselibrary.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Author {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String lastName;

  @ManyToMany
  @JoinTable(
      name = "author_book",
      joinColumns = @JoinColumn(name = "author_id"),
      inverseJoinColumns = @JoinColumn(name = "book_id"))
  private List<Book> books;

  public Author(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.books = new ArrayList<>();
  }

  public void addBook(Book book) {
    books.add(book);
  }

  public void removeBook(Book book) {
    books.remove(book);
  }
}
