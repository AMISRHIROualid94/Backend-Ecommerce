package ma.alten.backend.service.impl;

import lombok.RequiredArgsConstructor;
import ma.alten.backend.domain.Panier;
import ma.alten.backend.domain.Product;
import ma.alten.backend.dto.PanierDto;
import ma.alten.backend.dto.PanierItemDto;
import ma.alten.backend.exception.NotFoundException;
import ma.alten.backend.repo.PanierRepo;
import ma.alten.backend.repo.ProductRepo;
import ma.alten.backend.service.PanierService;
import ma.alten.backend.user.entity.UserEntity;
import ma.alten.backend.user.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PanierServiceImpl implements PanierService {

    private final PanierRepo panierRepo;
    private final ProductRepo productRepo;
    private final UserRepo userRepo;

    @Override
    public void addProductToPanier(String email, Long productId, int quantity) {
        UserEntity user = userRepo.findByEmail(email);
        if (user == null){
            throw new NotFoundException("User not found");
        }
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found"));
        Panier panier = panierRepo.findByUser(user);
        if (panier == null){
            panier = new Panier(user);
        }
        panier.addProduct(product, quantity);
        panierRepo.save(panier);
    }
    @Override
    public void removeProductFromPanier(String email, Long productId) {
        UserEntity user = userRepo.findByEmail(email);
        if (user == null){
            throw new NotFoundException("User not found");
        }
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found"));

        Panier panier = panierRepo.findByUser(user);
        if(panier == null){
            throw new NotFoundException("Panier not found");
        }

        panier.removeProduct(product);
        panierRepo.save(panier);
    }
    @Override
    public PanierDto getPanierByUserEntity(String email) {
        UserEntity user = userRepo.findByEmail(email);
        if (user == null){
            throw new NotFoundException("User not found");
        }

        Panier panier = panierRepo.findByUser(user);
        if(panier == null){
            throw new NotFoundException("Panier not found");
        }
        return convertToDto(panier);
    }
    @Override
    public PanierDto convertToDto(Panier panier) {
        PanierDto panierDto = new PanierDto();
        panierDto.setId(panier.getId());
        panierDto.setUserEmail(panier.getUser().getEmail());
        panierDto.setItems(panier.getItems().stream()
                .map(item -> new PanierItemDto(item.getProduct().getName(), item.getQuantity()))
                .collect(Collectors.toList()));
        return panierDto;
    }
}

