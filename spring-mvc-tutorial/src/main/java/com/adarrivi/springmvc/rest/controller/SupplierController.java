package com.adarrivi.springmvc.rest.controller;

import com.adarrivi.springmvc.core.domain.Supplier;
import com.adarrivi.springmvc.core.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Supplier> findAll() {
        return supplierService.findAll();
    }


    @RequestMapping(method = RequestMethod.POST)
    public void insert(@RequestBody Supplier newSupplier) {
        supplierService.insert(newSupplier);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void update(@RequestBody Supplier supplier) {
        supplierService.update(supplier);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable int id) {
        supplierService.delete(id);
    }
}
