package com.houselibrary.dto.response;

import com.houselibrary.model.Priority;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class BookDto {

  private Long bookId;
  private String title;
  private Long categoryId;
  private String categoryName;
  private Long subcategoryId;
  private String subcategoryName;
  private Priority priority;
  private List<AuthorDto> authors;
}
