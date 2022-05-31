package com.codegym.module5_backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IGeneralService <T>{
    Page<T> findAll(Pageable pageable);

    List<T> findAll();

    Optional<T> findById(Long id);

    T save(T t);

    void removeById(Long id);
}
