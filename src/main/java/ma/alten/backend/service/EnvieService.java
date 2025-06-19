package ma.alten.backend.service;

import ma.alten.backend.domain.Envie;

public interface EnvieService {

    Envie addProductToEnvie(Long envieId, Long productId);
    Envie removeProductFromEnvie(Long envieId, Long productId);
}
