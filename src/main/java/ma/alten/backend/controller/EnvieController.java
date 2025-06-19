package ma.alten.backend.controller;

import ma.alten.backend.domain.Envie;
import ma.alten.backend.service.EnvieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/envie")
public class EnvieController {

    @Autowired
    private EnvieService envieService;

    @PostMapping("/{envieId}/add/{productId}")
    public ResponseEntity<Envie> addProductToEnvie(@PathVariable Long envieId, @PathVariable Long productId) {
        Envie updatedEnvie = envieService.addProductToEnvie(envieId, productId);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedEnvie);
    }

    @DeleteMapping("/{envieId}/remove/{productId}")
    public ResponseEntity<Envie> removeProductFromEnvie(@PathVariable Long envieId, @PathVariable Long productId) {
        Envie updatedEnvie = envieService.removeProductFromEnvie(envieId, productId);
        return ResponseEntity.ok(updatedEnvie);
    }

}
