package com.houselibrary.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Subcategory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @ManyToOne private Category category;

  @OneToMany(mappedBy = "subcategory")
  private List<Book> books;

  public Subcategory(String name, Category category) {
    this.name = name;
    this.category = category;
  }
}
