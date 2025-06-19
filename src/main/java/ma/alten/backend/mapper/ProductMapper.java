package ma.alten.backend.mapper;

import ma.alten.backend.domain.Product;
import ma.alten.backend.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    Product toProduct(ProductDto productDto);
    ProductDto toProductDto(Product product);
    List<ProductDto> toProductDtos(List<Product> products);
    Product updateProductFromDto(ProductDto productDto, Product product);
}
