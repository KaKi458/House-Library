package com.houselibrary.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SubcategoryDto {

  private Long subcategoryId;
  private String subcategoryName;
  private Long categoryId;
  private String categoryName;
}
