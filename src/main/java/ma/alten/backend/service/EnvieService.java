package ma.alten.backend.service;

import ma.alten.backend.domain.Envie;
import ma.alten.backend.dto.EnvieDto;

public interface EnvieService {

    Envie addProductToEnvie(Long envieId, Long productId);
    EnvieDto removeProductFromEnvie(Long envieId, Long productId);
    Envie getEnvieById(Long envieId);
}
