package ma.alten.backend.controller;

import lombok.RequiredArgsConstructor;
import ma.alten.backend.domain.Envie;
import ma.alten.backend.dto.EnvieDto;
import ma.alten.backend.service.EnvieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/envie")
@RequiredArgsConstructor
public class EnvieController {

    private final EnvieService envieService;

    @PostMapping("/{envieId}/add/{productId}")
    public ResponseEntity<Envie> addProductToEnvie(@PathVariable Long envieId, @PathVariable Long productId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(envieService.addProductToEnvie(envieId, productId));
    }

    @DeleteMapping("/{envieId}/remove/{productId}")
    public ResponseEntity<EnvieDto> removeProductFromEnvie(@PathVariable Long envieId, @PathVariable Long productId) {
        return ResponseEntity.ok(envieService.removeProductFromEnvie(envieId, productId));
    }

}
