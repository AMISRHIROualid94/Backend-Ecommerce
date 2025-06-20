package ma.alten.backend.repo;

import ma.alten.backend.domain.Panier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PanierRepo extends JpaRepository<Panier,Long> {
    Optional<Panier> findByUser_Email(String email);
}
