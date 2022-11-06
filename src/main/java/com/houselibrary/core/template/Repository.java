package com.houselibrary.core.template;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface Repository<T extends Model> extends JpaRepository<T, Integer> {

    @Override
    List<T> findAll();

    Optional<T> findByName(String name);
}
