package com.houselibrary.repository;

import com.houselibrary.model.Category;
import com.houselibrary.model.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Integer> {

    @Override
    List<Subcategory> findAll();

    Subcategory findByName(String name);
}
