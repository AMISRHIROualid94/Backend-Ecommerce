package ma.alten.backend.service.impl;

import lombok.RequiredArgsConstructor;
import ma.alten.backend.constantes.Log;
import ma.alten.backend.domain.Product;
import ma.alten.backend.dto.PaginationResponse;
import ma.alten.backend.dto.ProductDto;
import ma.alten.backend.mapper.ProductMapper;
import ma.alten.backend.repo.ProductRepo;
import ma.alten.backend.service.EnvieService;
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
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepo productRepository;
    private final ProductMapper productMapper;
    private final ServiceHelper serviceHelper;
    private final EnvieService envieService;

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
    public ProductDto getProductById(Long id) {
        return productMapper.toProductDto(serviceHelper.findProductById(id));
    }

    @Override
    public void deleteProductById(Long id, Authentication authentication) {
        serviceHelper.adminAccess(authentication);
        logger.info(Log.DELETE_PRODUCT, id);
        productRepository.deleteById(id);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto,Authentication authentication) {
       serviceHelper.adminAccess(authentication);
       Product product = serviceHelper.findProductById(id);
       logger.info(Log.UPDATE_PRODUCT,id);
       return productMapper.toProductDto(productRepository.save(productMapper.updateProductFromDto(productDto,product)));
    }

    @Override
    public PaginationResponse getProductsInEnvie(Long envieId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Long> productsIds = envieService.getEnvieById(envieId).getProducts().stream().map(product -> product.getId()).collect(Collectors.toList());
        Page<Product> products = productsIds.isEmpty()? Page.empty(pageable) : productRepository.findProductsByIdIn(productsIds, pageable);
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
