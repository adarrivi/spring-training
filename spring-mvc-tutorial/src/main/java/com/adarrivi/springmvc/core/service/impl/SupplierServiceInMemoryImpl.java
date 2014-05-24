package com.adarrivi.springmvc.core.service.impl;

import com.adarrivi.springmvc.core.domain.Supplier;
import com.adarrivi.springmvc.core.exception.InvalidOperationException;
import com.adarrivi.springmvc.core.service.SupplierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
class SupplierServiceInMemoryImpl implements SupplierService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SupplierServiceInMemoryImpl.class);

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
        LOGGER.debug("Inserted {}", supplier);
    }

    @Override
    public void delete(int supplierId) {
        assertSupplierInMemory(supplierId);
        suppliersInMemoryMap.remove(supplierId);
        LOGGER.debug("Deleted id {}", supplierId);
    }

    private void assertSupplierInMemory(int supplierId) {
        if (!suppliersInMemoryMap.containsKey(supplierId)) {
            throw new InvalidOperationException("Supplier with id " + supplierId + " not found");
        }
    }

    @Override
    public void update(Supplier supplier) {
        int supplierId = supplier.getId();
        assertSupplierInMemory(supplierId);
        suppliersInMemoryMap.put(supplierId, supplier);
        LOGGER.debug("Updated {}", supplierId);
    }

    @Override
    public List<Supplier> findAll() {
        List<Supplier> suppliers = new ArrayList<>(suppliersInMemoryMap.values());
        LOGGER.debug("FindAll {}", suppliers);
        return suppliers;
    }
}
