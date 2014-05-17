package com.adarrivi.springdata.core.dao;

import java.util.List;

import com.adarrivi.springdata.core.entity.VehicleUsage;

public interface VehicleUsageRepository {

    List<VehicleUsage> findAll();
}
