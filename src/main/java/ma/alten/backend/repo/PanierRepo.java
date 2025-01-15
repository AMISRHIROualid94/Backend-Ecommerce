package ma.alten.backend.repo;

import ma.alten.backend.domain.Panier;
import ma.alten.backend.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PanierRepo extends JpaRepository<Panier,Long> {
    Panier findByUser(UserEntity userEntity);
}
