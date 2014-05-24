package com.adarrivi.springmvc.core.service.impl;

import com.adarrivi.springmvc.core.domain.Supplier;
import com.adarrivi.springmvc.core.exception.InvalidOperationException;
import com.adarrivi.springmvc.core.service.SupplierService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
class SupplierServiceInMemoryImpl implements SupplierService {

    // Concurrent hashMap allows us concurrent accesses to the map avoiding bottlenecks
    private Map<Integer, Supplier> suppliersInMemoryMap = new ConcurrentHashMap<>();

    SupplierServiceInMemoryImpl() {
        // To limit scope of the default constructor
    }

    SupplierServiceInMemoryImpl(Map<Integer, Supplier> suppliersInMemoryMap) {
        this.suppliersInMemoryMap = suppliersInMemoryMap;
    }


    @Override
    public void insert(Supplier supplier) {
        // putIfAbsent will put the supplier if it doesn't exist (in an atomic operation)
        Supplier previousValue = suppliersInMemoryMap.putIfAbsent(supplier.getId(), supplier);
        if (previousValue != null) {
            throw new InvalidOperationException("Supplier with id " + supplier.getId() + " already exists");
        }
    }

    @Override
    public void delete(Supplier supplier) {
        assertSupplierInMemory(supplier);
        suppliersInMemoryMap.remove(supplier.getId());
    }

    private void assertSupplierInMemory(Supplier supplier) {
        int supplierId = supplier.getId();
        if (!suppliersInMemoryMap.containsKey(supplierId)) {
            throw new InvalidOperationException("Supplier with id " + supplierId + " not found");
        }
    }

    @Override
    public void update(Supplier supplier) {
        assertSupplierInMemory(supplier);
        suppliersInMemoryMap.put(supplier.getId(), supplier);
    }

    @Override
    public List<Supplier> findAll() {
        return null;
    }
}
