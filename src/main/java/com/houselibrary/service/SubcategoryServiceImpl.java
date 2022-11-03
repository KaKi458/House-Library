package com.houselibrary.service;

import com.houselibrary.model.HouseLibraryException;
import com.houselibrary.model.Subcategory;
import com.houselibrary.repository.SubcategoryRepository;
import com.houselibrary.request.SubcategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubcategoryServiceImpl implements SubcategoryService {

    private final SubcategoryRepository subcategoryRepository;
    private CategoryService categoryService;

    @Autowired
    public SubcategoryServiceImpl(SubcategoryRepository subcategoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Subcategory addSubcategory(SubcategoryRequest request) {
        Subcategory subcategory = Subcategory.builder()
                .name(request.name())
                .category(categoryService.findByName(request.categoryName()))
                .build();
        subcategoryRepository.save(subcategory);
        return subcategory;
    }

    @Override
    public void deleteSubcategory(int subcategory_id) {
        if (subcategoryRepository.existsById(subcategory_id)) subcategoryRepository.deleteById(subcategory_id);
        else throw new HouseLibraryException(
                HttpStatus.NOT_FOUND, "The subcategory with id: " + subcategory_id + " does not exist.");
    }

    @Override
    public Subcategory getSubcategory(int subcategory_id) {
        Subcategory subcategory;
        Optional<Subcategory> optional = subcategoryRepository.findById(subcategory_id);
        if (optional.isPresent()) {
            subcategory = optional.get();
        } else {
            throw new HouseLibraryException(
                    HttpStatus.NOT_FOUND, "The subcategory with id: " + subcategory_id + " does not exist.");
        }
        return subcategory;
    }

    @Override
    public List<Subcategory> getAllSubcategories() {
        return subcategoryRepository.findAll();
    }

    @Override
    public int countAllSubcategories() {
        return (int) subcategoryRepository.count();
    }

    @Override
    public Subcategory findByName(String name) {
        Subcategory subcategory;
        Optional<Subcategory> optional = subcategoryRepository.findByName(name);
        if (optional.isPresent()) {
            subcategory = optional.get();
        } else {
            throw new HouseLibraryException(
                    HttpStatus.NOT_FOUND, "The subcategory with name: " + name + " does not exist.");
        }
        return subcategory;
    }
}
