package com.houselibrary.core.template;

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
