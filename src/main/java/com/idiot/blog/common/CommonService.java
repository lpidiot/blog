package com.idiot.blog.common;

import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName:CommonService
 * @Description:TODO
 * @Version:1.0
 **/
public class CommonService<T,ID> {
    @Autowired
    private CommonRepository<T,ID> repository;

    public List<T> findAll(){
        return repository.findAll();
    }

    public T findById(ID id){
        Optional<T> optional = repository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }
    public void save(T model){
        repository.save(model);
    }
    public void delete(T model){
        repository.delete(model);
    }
    public void deleteById(ID id){
        repository.deleteById(id);
    }

    public List<T> findAll(Specification spec){
        return repository.findAll(spec);
    }
    public Page<T> findAll(Specification spec, Pageable pageable) {
        return repository.findAll(spec,pageable);
    }
    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
