package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.InvoiceDetailRepository;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.Iservices.IInvoiceService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

public class InvoiceServiceImplTest {

    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private OperatorRepository operatorRepository;

    @Mock
    private InvoiceDetailRepository invoiceDetailRepository;

    @Mock
    private SupplierRepository supplierRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

@Test
void testRetrieveAllInvoices() {
    // Arrange
    List<Invoice> mockInvoices = new ArrayList<>();
    mockInvoices.add(new Invoice());
    mockInvoices.add(new Invoice());

    Mockito.when(invoiceRepository.findAll()).thenReturn(mockInvoices);

    // Act
    List<Invoice> result = invoiceService.retrieveAllInvoices();

    // Assert
    assertEquals(2, result.size());
}

    @Test
    void testCancelInvoice() {
        // Arrange
        Invoice mockInvoice = new Invoice();
        mockInvoice.setArchived(false);

        Mockito.when(invoiceRepository.findById(anyLong())).thenReturn(Optional.of(mockInvoice));
        Mockito.when(invoiceRepository.save(any(Invoice.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        invoiceService.cancelInvoice(1L);

        // Assert
        assertTrue(mockInvoice.getArchived());
    }

    @Test
    void testRetrieveInvoice() {
        // Arrange
        Invoice mockInvoice = new Invoice();
        Mockito.when(invoiceRepository.findById(anyLong())).thenReturn(Optional.of(mockInvoice));

        // Act
        Invoice result = invoiceService.retrieveInvoice(1L);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testGetInvoicesBySupplier() {
        // Arrange
        Supplier mockSupplier = new Supplier();
        Invoice invoice1 = new Invoice();
        Invoice invoice2 = new Invoice();
        mockSupplier.getInvoices().add(invoice1);
        mockSupplier.getInvoices().add(invoice2);
    
        Mockito.when(supplierRepository.findById(anyLong())).thenReturn(Optional.of(mockSupplier));
    
        // Act
        List<Invoice> result = invoiceService.getInvoicesBySupplier(1L);
    
        // Assert
        assertEquals(2, result.size());
    }
    

    @Test
    void testAssignOperatorToInvoice() {
        // Arrange
        Invoice mockInvoice = new Invoice();
        Operator mockOperator = new Operator();

        Mockito.when(invoiceRepository.findById(anyLong())).thenReturn(Optional.of(mockInvoice));
        Mockito.when(operatorRepository.findById(anyLong())).thenReturn(Optional.of(mockOperator));
        Mockito.when(operatorRepository.save(any(Operator.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        invoiceService.assignOperatorToInvoice(1L, 2L);

        // Assert
        assertTrue(mockOperator.getInvoices().contains(mockInvoice));
    }

    // Add more test cases for the remaining methods
}
