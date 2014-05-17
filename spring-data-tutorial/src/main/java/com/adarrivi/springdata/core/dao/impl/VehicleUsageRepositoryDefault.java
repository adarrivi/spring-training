package com.adarrivi.springdata.core.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.adarrivi.springdata.core.dao.VehicleUsageRepository;
import com.adarrivi.springdata.core.entity.VehicleUsage;

@Repository
class VehicleUsageRepositoryDefault implements VehicleUsageRepository {

    @Autowired
    private EntityManager entityManager;

    VehicleUsageRepositoryDefault() {
        // Limiting the constructor scope to enforce the usage through the
        // interface
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<VehicleUsage> findAll() {
        String sql = "SELECT v.car_class as carClass, COUNT(*) as timesUsed FROM booking b LEFT JOIN vehicle v ON b.vehicle_id = v.id GROUP BY v.car_class";
        Query query = entityManager.createNativeQuery(sql, VehicleUsage.class);
        return query.getResultList();
    }

}
