package com.houselibrary.service;

import com.houselibrary.model.Book;
import com.houselibrary.model.Model;
import com.houselibrary.repository.Repository;
import com.houselibrary.request.BookRequest;
import com.houselibrary.request.Request;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface Service<T extends Model> {

    T add(@NotNull Request<T> request);

    void delete(@NotNull int id);

    T get(@NotNull int id);

    List<T> getAll();

    int countAll();

    T findByName(@NotNull String name);
}
