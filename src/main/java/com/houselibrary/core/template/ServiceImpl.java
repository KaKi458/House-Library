package com.houselibrary.core.template;

import com.houselibrary.core.model.HouseLibraryException;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;

public abstract class ServiceImpl<T extends Model> implements Service<T> {

    protected Repository<T> repository;

    @Override
    public T add(Request<T> request) {
        T t = create(request);
        repository.save(t);
        return t;
    }

    @Override
    public T update(int id, Request<T> request) {
        T t = get(id);
        update(request);
        repository.save(t);
        return t;
    }

    @Override
    public void delete(int id) {
        if (repository.existsById(id)) {
            T t = get(id);
            clean(t);
            repository.deleteById(id);
        }
        else throw new HouseLibraryException(
                HttpStatus.NOT_FOUND, "The " + getClassName() + " with id: " + id + " does not exist.");
    }

    @Override
    public T get(int id) {
        T t;
        Optional<T> optional = repository.findById(id);
        if (optional.isPresent()) {
            t = optional.get();
        } else {
            String Class = null;
            throw new HouseLibraryException(
                    HttpStatus.NOT_FOUND, "The " + getClassName() + " with id: " + id + " does not exist.");
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

    @Override
    public T findByName(String name) {
        T t;
        Optional<T> optional = repository.findByName(name);
        if (optional.isPresent()) {
            t = optional.get();
        } else {
            throw new HouseLibraryException(
                    HttpStatus.NOT_FOUND, "The category with name: " + name + " does not exist.");
        }
        return t;
    }

    protected abstract T create(Request<T> request);
    protected abstract T update(Request<T> request);
    protected abstract void clean(T t);
    protected abstract String getClassName();

}
