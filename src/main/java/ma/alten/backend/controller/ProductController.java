package ma.alten.backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.alten.backend.domain.Product;
import ma.alten.backend.dto.ProductDto;
import ma.alten.backend.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto, Authentication authentication){
        service.adminAccess(authentication);
        logger.info("create a new product...");
        var entite = service.convertToProduct(productDto);
        entite.setId(null);
        Product product = service.createProduct(entite);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.convertToDto(product));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDto>> retreiveAll(){
        logger.info("retreive all products...");
       return ResponseEntity.ok(service.retreiveAllProducts()
               .stream()
               .map(service::convertToDto)
               .collect(Collectors.toList()));
    }

    @GetMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> getproductById(@PathVariable Long id){
        logger.info("get product with id : {}",id);
        return ResponseEntity.status(HttpStatus.FOUND).body(service.convertToDto(service.findProductById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id,Authentication authentication){
        service.adminAccess(authentication);
        logger.info("delete product with id : {}",id);
        service.deleteProductById(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

    @PatchMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto,Authentication authentication){
        service.adminAccess(authentication);
        var oldProduct = service.findProductById(id);

        if (productDto.getCode() != null) {
            oldProduct.setCode(productDto.getCode());
        }
        if (productDto.getName() != null) {
            oldProduct.setName(productDto.getName());
        }
        if (productDto.getDescription() != null) {
            oldProduct.setDescription(productDto.getDescription());
        }
        if (productDto.getImage() != null) {
            oldProduct.setImage(productDto.getImage());
        }
        if (productDto.getCategory() != null) {
            oldProduct.setCategory(productDto.getCategory());
        }
        if (productDto.getPrice() != null) {
            oldProduct.setPrice(productDto.getPrice());
        }
        if (productDto.getQuantity() != null) {
            oldProduct.setQuantity(productDto.getQuantity());
        }
        if (productDto.getInternalReference() != null) {
            oldProduct.setInternalReference(productDto.getInternalReference());
        }
        if (productDto.getShellId() != null) {
            oldProduct.setShellId(productDto.getShellId());
        }
        if (productDto.getInventoryStatus() != null) {
            oldProduct.setInventoryStatus(productDto.getInventoryStatus());
        }
        if (productDto.getRating() != null) {
            oldProduct.setRating(productDto.getRating());
        }
        if (productDto.getCreatedAt() != null) {
            oldProduct.setCreatedAt(productDto.getCreatedAt());
        }
        if (productDto.getUpdatedAt() != null) {
            oldProduct.setUpdatedAt(productDto.getUpdatedAt());
        }
        service.updateProduct(oldProduct);
        return ResponseEntity.ok("Product successfully updated");
    }
}
