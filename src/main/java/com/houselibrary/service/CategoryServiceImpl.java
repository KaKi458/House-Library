package com.houselibrary.service;

import com.houselibrary.mapper.ModelMapper;
import com.houselibrary.model.Category;
import com.houselibrary.model.HouseLibraryException;
import com.houselibrary.repository.CategoryRepository;
import com.houselibrary.request.CategoryRequest;
import com.houselibrary.response.CategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryServiceImpl(ModelMapper modelMapper, CategoryRepository categoryRepository) {
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponse addCategory(CategoryRequest request) {
        Category category = Category.builder().name(request.name()).build();
        categoryRepository.save(category);
        return modelMapper.map(category);
    }

    @Override
    public void deleteCategory(int category_id) {
        if(categoryRepository.existsById(category_id)) categoryRepository.deleteById(category_id);
        else throw new HouseLibraryException(
                HttpStatus.NOT_FOUND, "The category with id: " + category_id + " does not exist.");
    }

    @Override
    public CategoryResponse getCategory(int category_id) {
        Category category;
        Optional<Category> optional = categoryRepository.findById(category_id);
        if(optional.isPresent()) {
            category = optional.get();
        } else {
            throw new HouseLibraryException(
                    HttpStatus.NOT_FOUND, "The category with id: " + category_id + " does not exist.");
        }
        return modelMapper.map(category);
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return modelMapper.mapCategories(categories);
    }

    @Override
    public int countAllCategories() {
        return (int)categoryRepository.count();
    }

    @Override
    public CategoryResponse findByName(String name) {
        Category category = categoryRepository.findByName(name);
        return modelMapper.map(category);
    }
}
