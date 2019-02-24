package com.example.spring_mvc_demo.service.core;

import com.example.spring_mvc_demo.model.ElectronicComponent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ElectronicComponentRepository {

    ElectronicComponent find(final Long id);

    Page<ElectronicComponent> find(final Pageable pageable);

    List<ElectronicComponent> findAll();

    ElectronicComponent save(final ElectronicComponent component);

    void delete(final Long id);
}
