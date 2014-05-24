package com.adarrivi.springmvc.core.service;

import com.adarrivi.springmvc.core.domain.Supplier;

import java.util.List;

public interface SupplierService {

    void insert(Supplier supplier);

    void delete(Supplier supplier);

    void update(Supplier supplier);

    List<Supplier> findAll();
}
