package ma.alten.backend.service;

import ma.alten.backend.domain.Panier;
import ma.alten.backend.dto.PanierDto;

public interface PanierService {

    void addProductToPanier(String email, Long productId, int quantity);
    void removeProductFromPanier(String email, Long productId);
    PanierDto getPanierByUserEntity(String email);
    PanierDto convertToDto(Panier panier);
}
