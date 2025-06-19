package ma.alten.backend.service.impl;

import lombok.RequiredArgsConstructor;
import ma.alten.backend.constantes.ExceptionConst;
import ma.alten.backend.constantes.Log;
import ma.alten.backend.domain.Product;
import ma.alten.backend.dto.PaginationResponse;
import ma.alten.backend.dto.ProductDto;
import ma.alten.backend.exception.NotFoundException;
import ma.alten.backend.mapper.ProductMapper;
import ma.alten.backend.repo.ProductRepo;
import ma.alten.backend.service.ProductService;
import ma.alten.backend.service.ServiceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepo productRepository;
    private final ProductMapper productMapper;
    private final ServiceHelper serviceHelper;

    @Override
    public ProductDto createProduct(ProductDto newProduct,Authentication authentication) {
        serviceHelper.adminAccess(authentication);
        logger.info(Log.CREATE_NEW_PRODUCT);
        Product product = productRepository.save(productMapper.toProduct(newProduct));
        return productMapper.toProductDto(product);
    }

    @Override
    public List<ProductDto> retreiveAllProducts() {
        logger.info(Log.RETRIEVE_ALL_PRODUCT);
        return productMapper.toProductDtos(productRepository.findAll());
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format(ExceptionConst.PRODUCT_NOT_FOUND,id)));
    }

    @Override
    public ProductDto getProductById(Long id) {
        return productMapper.toProductDto(findProductById(id));
    }

    @Override
    public void deleteProductById(Long id, Authentication authentication) {
        serviceHelper.adminAccess(authentication);
        logger.info(Log.DELETE_PRODUCT, id);
        productRepository.deleteById(id);
    }

    @Override
    public void updateProduct(Long id, ProductDto productDto,Authentication authentication) {
       serviceHelper.adminAccess(authentication);
       Product product = findProductById(id);
       logger.info(Log.UPDATE_PRODUCT,id);
       productRepository.save(productMapper.updateProductFromDto(productDto,product));
    }

    @Override
    public PaginationResponse getProductsInEnvie(Long envieId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findProductsByEnvieId(envieId,pageable);
        return PaginationResponse.builder()
                .objects(productMapper.toProductDtos(products.getContent()))
                .pageNumber(page)
                .pageSize(size)
                .totalElements(products.getTotalElements())
                .totalPages(products.getTotalPages())
                .last(products.isLast())
                .build();
    }
}
