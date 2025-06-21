package ma.alten.backend.controller;

import lombok.RequiredArgsConstructor;
import ma.alten.backend.dto.PanierDto;
import ma.alten.backend.service.PanierService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paniers")
@RequiredArgsConstructor
public class PanierController {

    private final PanierService panierService;
    
    @PostMapping(value ="/add/{productId}")
    public ResponseEntity<PanierDto> addProductToPanier(@PathVariable Long productId, @RequestParam int quantity, Authentication authentication) {
        return ResponseEntity.ok(panierService.addProductToPanier(authentication.getName(), productId, quantity));
    }
    
    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<PanierDto> removeProductFromPanier(@PathVariable Long productId, Authentication authentication) {
        return ResponseEntity.ok(panierService.removeProductFromPanier(authentication.getName(), productId));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PanierDto> getPanier(Authentication authentication) {
        return ResponseEntity.ok(panierService.getPanier(authentication.getName()));
    }
}

