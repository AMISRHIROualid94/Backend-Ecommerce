package ma.alten.backend.service;

import ma.alten.backend.domain.Product;
import ma.alten.backend.dto.ProductDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ProductService {
    Product createProduct(Product newProduct);
    List<Product> retreiveAllProducts();
    Product findProductById(Long id);
    Product isProductExists(Long id);
    void deleteProductById(Long id);
    void updateProduct(Product product);
    Product convertToProduct(ProductDto productDto);
    void adminAccess(Authentication authentication);
    ProductDto convertToDto(Product product);
}
