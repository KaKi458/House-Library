package com.houselibrary.repository;

import com.houselibrary.model.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Integer> {

    Optional<Subcategory> findByName(String name);
}
