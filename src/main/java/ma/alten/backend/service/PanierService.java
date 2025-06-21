package ma.alten.backend.service;

import ma.alten.backend.domain.Panier;
import ma.alten.backend.dto.PanierDto;

public interface PanierService {

    PanierDto addProductToPanier(String email, Long productId, int quantity);
    Panier getPanierByUserEmail(String email);
    PanierDto getPanier(String email);
    PanierDto removeProductFromPanier(String email, Long productId);
}
