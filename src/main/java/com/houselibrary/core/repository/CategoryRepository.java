package com.houselibrary.core.repository;

import com.houselibrary.core.model.Category;
import com.houselibrary.core.template.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface CategoryRepository extends Repository<Category> {
}
