package ma.alten.backend.controller;

import lombok.RequiredArgsConstructor;
import ma.alten.backend.dto.PanierDto;
import ma.alten.backend.service.PanierService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/paniers")
@RequiredArgsConstructor
public class PanierController {

    private final PanierService panierService;
    
    @PostMapping(value ="/add/{productId}")
    public ResponseEntity<String> addProductToPanier(@PathVariable Long productId, @RequestParam int quantity, Authentication authentication) {
        String email = authentication.getName();
        panierService.addProductToPanier(email, productId, quantity);
        return ResponseEntity.ok("Product added successfully");
    }
    
    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<String> removeProductFromPanier(@PathVariable Long productId, Authentication authentication) {
        String email = authentication.getName();
        panierService.removeProductFromPanier(email, productId);
        return ResponseEntity.ok("Product deleted from the panier");
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PanierDto> getPanier(Authentication authentication) {
        String email = authentication.getName();
        PanierDto panierDto = panierService.getPanierByUserEntity(email);
        return ResponseEntity.ok(panierDto);
    }
}

