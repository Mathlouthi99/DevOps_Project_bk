package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.ProductRepository;
import tn.esprit.devops_project.repositories.StockRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private StockRepository stockRepository;

    @BeforeEach
    void setUp() {
        // Initialize any mock data or configurations if needed.
    }

    @Test
    void testAddProduct() {
        // Arrange
        Long stockId = 1L;
        Product product = new Product();
        Stock stock = new Stock();
        stock.setIdStock(stockId);
        product.setTitle("Product Title");
        product.setStock(stock);

        when(stockRepository.findById(stockId)).thenReturn(Optional.of(stock));
        when(productRepository.save(product)).thenReturn(product);

        // Act
        Product addedProduct = productService.addProduct(product, stockId);

        // Assert
        assertEquals(product, addedProduct);
    }

    @Test
    void testRetrieveProduct() {
        // Arrange
        Long productId = 1L;
        Product product = new Product();
        product.setIdProduct(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // Act
        Product retrievedProduct = productService.retrieveProduct(productId);

        // Assert
        assertEquals(productId, retrievedProduct.getIdProduct());
    }

    @Test
    void testRetreiveAllProduct() {
        // Arrange
        List<Product> products = Collections.singletonList(new Product());

        when(productRepository.findAll()).thenReturn(products);

        // Act
        List<Product> retrievedProducts = productService.retreiveAllProduct();

        // Assert
        assertEquals(products, retrievedProducts);
    }

    @Test
    void testRetrieveProductByCategory() {
        // Arrange
        ProductCategory category = ProductCategory.ELECTRONICS;
        List<Product> products = Collections.singletonList(new Product());

        when(productRepository.findByCategory(category)).thenReturn(products);

        // Act
        List<Product> retrievedProducts = productService.retrieveProductByCategory(category);

        // Assert
        assertEquals(products, retrievedProducts);
    }

    @Test
    void testDeleteProduct() {
        // Arrange
        Long productId = 1L;

        // No need to configure mock behavior for delete operation.

        // Act
        productService.deleteProduct(productId);

        // Assert
        // Verify that the delete operation is called on the repository, if applicable.
    }

    @Test
    void testRetreiveProductStock() {
        // Arrange
        Long stockId = 1L;
        List<Product> products = Collections.singletonList(new Product());

        when(productRepository.findByStockIdStock(stockId)).thenReturn(products);

        // Act
        List<Product> retrievedProducts = productService.retreiveProductStock(stockId);

        // Assert
        assertEquals(products, retrievedProducts);
    }
}
