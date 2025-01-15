package ma.alten.backend.service.impl;

import lombok.RequiredArgsConstructor;
import ma.alten.backend.domain.Product;
import ma.alten.backend.dto.ProductDto;
import ma.alten.backend.exception.NotFoundException;
import ma.alten.backend.repo.ProductRepo;
import ma.alten.backend.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo repo;
    private final ModelMapper mapper;

    @Override
    public Product createProduct(Product newProduct) {
        return repo.save(newProduct);
    }

    @Override
    public List<Product> retreiveAllProducts() {
        return repo.findAll();
    }

    @Override
    public Product findProductById(Long id) {
        return isProductExists(id);
    }

    @Override
    public void deleteProductById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public void updateProduct(Product product) {
        repo.save(product);
    }

    @Override
    public Product convertToProduct(ProductDto productDto) {
        return mapper.map(productDto,Product.class);
    }

    @Override
    public void adminAccess(Authentication authentication) {
        if (!"admin@admin.com".equals(authentication.getName())) {
            throw new AccessDeniedException("You are not allowed to create new product.");
        }
        return;
    }

    @Override
    public ProductDto convertToDto(Product product) {
        return mapper.map(product,ProductDto.class);
    }

    @Override
    public Product isProductExists(Long id) {
        return repo.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Product by id " + id + " not found")
                );
    }
}
