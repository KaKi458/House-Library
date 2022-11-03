package com.houselibrary.service;

import com.houselibrary.mapper.ModelMapper;
import com.houselibrary.model.Subcategory;
import com.houselibrary.model.HouseLibraryException;
import com.houselibrary.repository.CategoryRepository;
import com.houselibrary.repository.SubcategoryRepository;
import com.houselibrary.request.SubcategoryRequest;
import com.houselibrary.response.SubcategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubcategoryServiceImpl implements SubcategoryService {

    private final ModelMapper modelMapper;
    private final SubcategoryRepository subcategoryRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public SubcategoryServiceImpl(ModelMapper modelMapper, SubcategoryRepository subcategoryRepository, CategoryRepository categoryRepository) {
        this.modelMapper = modelMapper;
        this.subcategoryRepository = subcategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public SubcategoryResponse addSubcategory(SubcategoryRequest request) {
        Subcategory subcategory = Subcategory.builder()
                .name(request.name())
                .parent(categoryRepository.findByName(request.name()))
                .build();
        subcategoryRepository.save(subcategory);
        return modelMapper.map(subcategory);
    }

    @Override
    public void deleteSubcategory(int subcategory_id) {
        if(subcategoryRepository.existsById(subcategory_id)) subcategoryRepository.deleteById(subcategory_id);
        else throw new HouseLibraryException(
                HttpStatus.NOT_FOUND, "The subcategory with id: " + subcategory_id + " does not exist.");
    }

    @Override
    public SubcategoryResponse getSubcategory(int subcategory_id) {
        Subcategory subcategory;
        Optional<Subcategory> optional = subcategoryRepository.findById(subcategory_id);
        if(optional.isPresent()) {
            subcategory = optional.get();
        } else {
            throw new HouseLibraryException(
                    HttpStatus.NOT_FOUND, "The subcategory with id: " + subcategory_id + " does not exist.");
        }
        return modelMapper.map(subcategory);
    }

    @Override
    public List<SubcategoryResponse> getAllSubcategories() {
        List<Subcategory> subcategories = subcategoryRepository.findAll();
        return modelMapper.mapSubcategories(subcategories);
    }

    @Override
    public int countAllSubcategories() {
        return (int)subcategoryRepository.count();
    }

    @Override
    public SubcategoryResponse findByName(String name) {
        Subcategory subcategory = subcategoryRepository.findByName(name);
        return modelMapper.map(subcategory);
    }
}
