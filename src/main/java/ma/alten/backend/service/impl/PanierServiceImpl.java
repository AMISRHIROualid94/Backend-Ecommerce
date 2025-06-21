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
import ma.alten.backend.service.ServiceHelper;
import ma.alten.backend.domain.UserEntity;
import ma.alten.backend.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PanierServiceImpl implements PanierService {

    private final PanierRepo panierRepo;
    private final ServiceHelper serviceHelper;
    private final UserService userService;
    private final PanierMapper panierMapper;

    @Override
    public PanierDto addProductToPanier(String email, Long productId, int quantity) {
        UserEntity user = userService.searchByEmail(email);
        Product product = serviceHelper.findProductById(productId);
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
    public PanierDto removeProductFromPanier(String email, Long productId) {
        Product product = serviceHelper.findProductById(productId);
        Panier panier = getPanierByUserEmail(email);
        panier.removeProduct(product);
        return panierMapper.toPanierDto(panierRepo.save(panier));
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

