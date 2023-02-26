package com.houselibrary.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  @ManyToMany(fetch = FetchType.EAGER, mappedBy = "books")
  private List<Author> authors;

  @ManyToOne(fetch = FetchType.LAZY)
  private Subcategory subcategory;

  //    @Enumerated(EnumType.STRING)
  //    private Priority priority;
}
