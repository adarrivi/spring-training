package com.adarrivi.springmvc.core.service.impl;

import com.adarrivi.springmvc.core.domain.Supplier;

//Helper class to facilitate the object creation for testing proposes
public class TestMvcBuilder {

    private TestMvcBuilder() {
        //Static class, should not be instantiated
    }

    public static Supplier createSupplier(int id) {
        return new Supplier(id, "hertz");
    }
}
