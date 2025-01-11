package com.homelibrary.api.request;

import lombok.Getter;
import java.util.List;

@Getter
public class BookRequest {

  private String title;
  private String category;
  private String subcategory;
  private List<AuthorRequest> authors;
  private Integer priority;
}
