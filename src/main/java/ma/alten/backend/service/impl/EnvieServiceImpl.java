package ma.alten.backend.service.impl;

import lombok.RequiredArgsConstructor;
import ma.alten.backend.constantes.ExceptionConst;
import ma.alten.backend.domain.Envie;
import ma.alten.backend.domain.Product;
import ma.alten.backend.dto.EnvieDto;
import ma.alten.backend.exception.NotFoundException;
import ma.alten.backend.mapper.EnvieMapper;
import ma.alten.backend.repo.EnvieRepo;
import ma.alten.backend.service.EnvieService;
import ma.alten.backend.service.ServiceHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EnvieServiceImpl implements EnvieService {

    private final EnvieRepo envieRepo;
    private final ServiceHelper serviceHelper;
    private final EnvieMapper envieMapper;

    @Override
    public Envie addProductToEnvie(Long envieId, Long productId) {
        Envie envie = getEnvieById(envieId);
        Product product = serviceHelper.findProductById(productId);
        envie.getProducts().add(product);
        return envieRepo.save(envie);
    }

    @Override
    public EnvieDto removeProductFromEnvie(Long envieId, Long productId) {
        Envie envie = getEnvieById(envieId);
        Product product = serviceHelper.findProductById(productId);
        envie.getProducts().remove(product);
        return envieMapper.toEnvieDto(envieRepo.save(envie));
    }

    @Override
    public Envie getEnvieById(Long envieId) {
        return envieRepo.findById(envieId).orElseThrow(() -> new NotFoundException(String.format(ExceptionConst.ENVIE_NOT_FOUND, envieId)));
    }
}
