package ma.alten.backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.alten.backend.dto.PaginationResponse;
import ma.alten.backend.dto.ProductDto;
import ma.alten.backend.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto, Authentication authentication){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productDto, authentication));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDto>> retreiveAll(){
       return ResponseEntity.ok(productService.retreiveAllProducts());
    }

    @GetMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> getproductById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND).body(productService.getProductById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id,Authentication authentication){
        productService.deleteProductById(id,authentication);
        return ResponseEntity.ok("Product deleted successfully");
    }

    @GetMapping("/{envieId}/products")
    public ResponseEntity<PaginationResponse> getProductsInEnvie(@PathVariable Long envieId, @RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
        return ResponseEntity.ok(productService.getProductsInEnvie(envieId,page,size));
    }

    @PatchMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto,Authentication authentication){
        return ResponseEntity.ok(productService.updateProduct(id,productDto,authentication));
    }
}
