package com.homelibrary.api.response;

import com.homelibrary.model.Author;

import java.util.ArrayList;
import java.util.List;

public record AuthorRecord(Integer authorId, String firstName, String lastName){

    public static List<AuthorRecord> getAuthors(List<Author> authorModelList) {
        List<AuthorRecord> authorRecordList = new ArrayList<>();
        authorModelList.forEach(a -> authorRecordList.add(
                new AuthorRecord(a.getId(), a.getFirstName(), a.getLastName())));
        return authorRecordList;
    }
}
