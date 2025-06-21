package ma.alten.backend.dto;

import ma.alten.backend.domain.Product;
import ma.alten.backend.domain.UserEntity;

import java.util.List;

public record EnvieDto(
           UserEntity user,
           List<Product> products) {
}
