package com.houselibrary.repository;

import com.houselibrary.model.Category;
import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface CategoryRepository extends Repository<Category> {

    Optional<Category> findByName(String name);
}
