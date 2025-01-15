package ma.alten.backend.service.impl;

import lombok.RequiredArgsConstructor;
import ma.alten.backend.domain.Envie;
import ma.alten.backend.domain.Product;
import ma.alten.backend.exception.NotFoundException;
import ma.alten.backend.repo.EnvieRepo;
import ma.alten.backend.repo.ProductRepo;
import ma.alten.backend.service.EnvieService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnvieServiceImpl implements EnvieService {

    private final EnvieRepo envieRepo;
    private final ProductRepo productRepo;
    
    @Override
    public Envie addProductToEnvie(Long envieId, Long productId) {
        Envie envie = envieRepo.findById(envieId).orElseThrow(() -> new NotFoundException("Envie not found"));
        Product product = productRepo.findById(productId).orElseThrow(() -> new NotFoundException("Product not found"));
        envie.getProducts().add(product);
        return envieRepo.save(envie);
    }

    @Override
    public Envie removeProductFromEnvie(Long envieId, Long productId) {
        Envie envie = envieRepo.findById(envieId).orElseThrow(() -> new NotFoundException("Envie not found"));
        Product product = productRepo.findById(productId).orElseThrow(() -> new NotFoundException("Product not found"));

        envie.getProducts().remove(product);
        return envieRepo.save(envie);
    }

    @Override
    public List<Product> getProductsInEnvie(Long envieId) {
        Envie envie = envieRepo.findById(envieId).orElseThrow(() -> new NotFoundException("Envie not found"));
        return envie.getProducts();
    }
}
