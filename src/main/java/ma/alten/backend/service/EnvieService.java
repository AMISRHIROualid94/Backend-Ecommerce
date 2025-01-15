package ma.alten.backend.service;

import ma.alten.backend.domain.Envie;
import ma.alten.backend.domain.Product;

import java.util.List;

public interface EnvieService {

    Envie addProductToEnvie(Long envieId, Long productId);
    Envie removeProductFromEnvie(Long envieId, Long productId);
    List<Product> getProductsInEnvie(Long envieId);
}
