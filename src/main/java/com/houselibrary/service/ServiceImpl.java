package com.houselibrary.service;

import com.houselibrary.model.HouseLibraryException;
import com.houselibrary.model.Model;
import com.houselibrary.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

public abstract class ServiceImpl<T extends Model> implements Service<T> {

    protected Repository<T> repository;

    @Override
    public void delete(int id) {
        if (repository.existsById(id)) repository.deleteById(id);
        else throw new HouseLibraryException(
                HttpStatus.NOT_FOUND, "The " + " with id: " + id + " does not exist.");
    }

    @Override
    public T get(int id) {
        T t;
        Optional<T> optional = repository.findById(id);
        if (optional.isPresent()) {
            t = optional.get();
        } else {
            throw new HouseLibraryException(
                    HttpStatus.NOT_FOUND, "The book with id: " + id + " does not exist.");
        }
        return t;
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public int countAll() {
        return (int) repository.count();
    }

//    @Override
//    public T findByName(String name) {
//        T t;
//        Optional<T> optional = repository.findByName(name);
//        if (optional.isPresent()) {
//            t = optional.get();
//        } else {
//            throw new HouseLibraryException(
//                    HttpStatus.NOT_FOUND, "The category with name: " + name + " does not exist.");
//        }
//        return t;
//    }
}
