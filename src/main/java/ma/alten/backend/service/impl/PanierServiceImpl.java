package ma.alten.backend.service.impl;

import lombok.RequiredArgsConstructor;
import ma.alten.backend.constantes.ExceptionConst;
import ma.alten.backend.domain.Panier;
import ma.alten.backend.domain.Product;
import ma.alten.backend.dto.PanierDto;
import ma.alten.backend.exception.NotFoundException;
import ma.alten.backend.mapper.PanierMapper;
import ma.alten.backend.repo.PanierRepo;
import ma.alten.backend.service.PanierService;
import ma.alten.backend.service.ProductService;
import ma.alten.backend.user.entity.UserEntity;
import ma.alten.backend.user.services.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PanierServiceImpl implements PanierService {

    private final PanierRepo panierRepo;
    private final ProductService  productService;
    private final UserService userService;
    private final PanierMapper panierMapper;

    @Override
    public PanierDto addProductToPanier(String email, Long productId, int quantity) {
        UserEntity user = userService.searchByEmail(email);
        Product product = productService.findProductById(productId);
        Panier panier = panierRepo.findByUser_Email(email).orElse(null);
        if (panier == null){
            panier = Panier.builder()
                    .user(user)
                    .build();
        }
        panier.addProduct(product, quantity);
        return panierMapper.toPanierDto(panierRepo.save(panier));
    }

    @Override
    public void removeProductFromPanier(String email, Long productId) {
        Product product = productService.findProductById(productId);
        Panier panier = getPanierByUserEmail(email);
        panier.removeProduct(product);
        panierRepo.save(panier);
    }
    @Override
    public Panier getPanierByUserEmail(String email) {
        return panierRepo.findByUser_Email(email).orElseThrow(() -> new NotFoundException(String.format(ExceptionConst.PANIER_NOT_FOUND, email)));
    }

    @Override
    public PanierDto getPanier(String email) {
        return panierMapper.toPanierDto(getPanierByUserEmail(email));
    }
}

