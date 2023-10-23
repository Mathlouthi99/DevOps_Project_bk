package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.SupplierRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SupplierServiceImplTest {

    @InjectMocks
    private SupplierServiceImpl supplierService;

    @Mock
    private SupplierRepository supplierRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllSuppliers() {
        Supplier supplier = new Supplier();
        when(supplierRepository.findAll()).thenReturn(Collections.singletonList(supplier));

        List<Supplier> suppliers = supplierService.retrieveAllSuppliers();

        assertEquals(1, suppliers.size());
    }

    @Test
    public void testAddSupplier() {
        Supplier supplier = new Supplier();
        when(supplierRepository.save(supplier)).thenReturn(supplier);

        Supplier addedSupplier = supplierService.addSupplier(supplier);

        assertEquals(supplier, addedSupplier);
    }

    @Test
    public void testUpdateSupplier() {
        Supplier supplier = new Supplier();
        when(supplierRepository.save(supplier)).thenReturn(supplier);

        Supplier updatedSupplier = supplierService.updateSupplier(supplier);

        assertEquals(supplier, updatedSupplier);
    }

    @Test
    public void testDeleteSupplier() {
        Long supplierId = 1L;
        supplierService.deleteSupplier(supplierId);

        Mockito.verify(supplierRepository).deleteById(supplierId);
    }

    @Test
    public void testRetrieveSupplier() {
        Long supplierId = 1L;
        Supplier supplier = new Supplier();
        when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(supplier));

        Supplier retrievedSupplier = supplierService.retrieveSupplier(supplierId);

        assertEquals(supplier, retrievedSupplier);
    }

    @Test
    public void testRetrieveSupplierWithInvalidId() {
        Long invalidId = 99L;
        when(supplierRepository.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> supplierService.retrieveSupplier(invalidId));
    }
}
