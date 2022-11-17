package com.houselibrary.core.repository;

import com.houselibrary.core.model.Author;
import com.houselibrary.core.template.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Transactional
public interface AuthorRepository extends Repository<Author> {

    List<Author> findByLastName(String lastName);

    @Override
    default Optional<Author> findByName(String name) {
        return findByLastName(name).stream().findAny();
    }

    default Optional<Author> findByName(String firstName, String lastName) {
        return findByLastName(lastName)
                .stream()
                .filter(author -> Objects.equals(author.getLastName(), lastName))
                .findAny();
    }
}
