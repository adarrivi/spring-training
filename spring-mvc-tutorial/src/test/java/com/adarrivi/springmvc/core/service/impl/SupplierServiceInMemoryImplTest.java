package com.adarrivi.springmvc.core.service.impl;


import com.adarrivi.springmvc.core.domain.Supplier;
import com.adarrivi.springmvc.core.exception.InvalidOperationException;
import com.adarrivi.springmvc.core.service.SupplierService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SupplierServiceInMemoryImplTest {

    private static final String NEW_SUPPLIER_NAME = "hertz australia";

    private SupplierService victim;
    private Map<Integer, Supplier> inMemorySuppliers;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Supplier inputSupplier;
    private List<Supplier> outputSuppliers;

    @Before
    public void setUp() {
        inMemorySuppliers = new HashMap<>();
        victim = new SupplierServiceInMemoryImpl(inMemorySuppliers);
    }

    @Test
    public void insert_IdAlreadyExists_ThrowsInvalidOpException() {
        thrown.expect(InvalidOperationException.class);
        Supplier alreadyInMemory = TestMvcBuilder.createSupplier(1);
        givenSupplierAlreadyInMemory(alreadyInMemory);
        givenSupplier(alreadyInMemory);
        whenInsert();
    }

    private void givenSupplierAlreadyInMemory(Supplier supplier) {
        inMemorySuppliers.put(supplier.getId(), supplier);
    }

    private void givenSupplier(Supplier supplier) {
        inputSupplier = supplier;
    }

    private void whenInsert() {
        victim.insert(inputSupplier);
    }

    @Test
    public void insert_EmptyMemory_PutsSupplierInMemory() {
        Supplier supplier = TestMvcBuilder.createSupplier(1);
        givenSupplier(supplier);
        whenInsert();
        thenMemoryShouldContain(supplier);
    }

    private void thenMemoryShouldContain(Supplier expectedSupplier) {
        Assert.assertTrue(inMemorySuppliers.containsKey(expectedSupplier.getId()));
        Assert.assertTrue(inMemorySuppliers.containsValue(expectedSupplier));
    }

    @Test
    public void insert_NoEmptyMemory_PutsSupplierInMemory() {
        givenSupplierAlreadyInMemory(TestMvcBuilder.createSupplier(1));
        givenSupplierAlreadyInMemory(TestMvcBuilder.createSupplier(2));
        Supplier supplierToInsert = TestMvcBuilder.createSupplier(3);
        givenSupplier(supplierToInsert);
        whenInsert();
        thenMemoryShouldContain(supplierToInsert);
    }


    @Test
    public void delete_SupplierDoesntExist_ThrowsInvalidOpEx() {
        thrown.expect(InvalidOperationException.class);
        Supplier supplierToDelete = TestMvcBuilder.createSupplier(3);
        givenSupplier(supplierToDelete);
        whenDelete();
    }

    private void whenDelete() {
        victim.delete(inputSupplier);
    }

    private void thenSupplierShouldNotBeInMemory(Supplier supplier) {
        Assert.assertFalse(inMemorySuppliers.containsKey(supplier.getId()));
    }

    @Test
    public void delete_SupplierExists_SupplierNotInMemory() {
        Supplier supplierToDelete = TestMvcBuilder.createSupplier(1);
        givenSupplierAlreadyInMemory(supplierToDelete);
        givenSupplierAlreadyInMemory(TestMvcBuilder.createSupplier(2));
        givenSupplier(supplierToDelete);
        whenDelete();
        thenSupplierShouldNotBeInMemory(supplierToDelete);
    }

    @Test
    public void update_SupplierDoesntExist_ThrowsInvalidOpEx() {
        thrown.expect(InvalidOperationException.class);
        Supplier supplierToUpdate = TestMvcBuilder.createSupplier(1);
        givenSupplier(supplierToUpdate);
        whenUpdate();
    }

    private void whenUpdate() {
        victim.update(inputSupplier);
    }

    @Test
    public void update_SupplierExists_UpdatesSupplier() {
        int supplierId = 1;
        Supplier supplier = TestMvcBuilder.createSupplier(supplierId);
        givenSupplierAlreadyInMemory(supplier);
        Supplier supplierToUpdate = TestMvcBuilder.createSupplier(supplierId);
        supplierToUpdate.setName(NEW_SUPPLIER_NAME);
        givenSupplier(supplierToUpdate);
        whenUpdate();
        thenSupplierNameWithIdShouldBe(supplierId, NEW_SUPPLIER_NAME);
    }

    private void thenSupplierNameWithIdShouldBe(int supplierId, String expectedName) {
        Supplier supplier = inMemorySuppliers.get(supplierId);
        Assert.assertNotNull("supplier not found in memory", supplier);
        Assert.assertEquals(expectedName, supplier.getName());
    }

    @Test
    public void findAll_Empty_ReturnsEmptyList() {
        whenFindAll();
        thenSupplierListShouldBeEmpty();
    }

    private void whenFindAll() {
        outputSuppliers = victim.findAll();
    }

    private void thenSupplierListShouldBeEmpty() {
        Assert.assertNotNull(outputSuppliers);
        Assert.assertTrue(outputSuppliers.isEmpty());
    }

    @Test
    public void findAll_Supplier_ReturnsSupplierInList() {
        Supplier supplier = TestMvcBuilder.createSupplier(1);
        givenSupplierAlreadyInMemory(supplier);
        whenFindAll();
        thenSupplierListShouldContain(supplier);
    }

    private void thenSupplierListShouldContain(Supplier expectedSupplier) {
        Assert.assertTrue(inMemorySuppliers.containsKey(expectedSupplier.getId()));
    }

}
