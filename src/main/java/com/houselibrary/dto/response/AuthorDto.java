package com.houselibrary.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AuthorDto {

  private Long authorId;
  private String firstName;
  private String lastName;
}
