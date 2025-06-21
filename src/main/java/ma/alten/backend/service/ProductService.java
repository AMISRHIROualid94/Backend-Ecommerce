package ma.alten.backend.service;

import ma.alten.backend.dto.PaginationResponse;
import ma.alten.backend.dto.ProductDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto newProduct, Authentication authentication);
    List<ProductDto> retreiveAllProducts();
    ProductDto getProductById(Long id);
    void deleteProductById(Long id, Authentication authentication);
    ProductDto updateProduct(Long id, ProductDto productDto, Authentication authentication);
    PaginationResponse getProductsInEnvie(Long envieId, int page, int size);
}
