package com.houselibrary.repository;

import com.houselibrary.model.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface SubcategoryRepository extends Repository<Subcategory> {

    Optional<Subcategory> findByName(String name);
}
