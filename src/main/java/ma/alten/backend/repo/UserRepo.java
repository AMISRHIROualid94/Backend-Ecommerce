package ma.alten.backend.repo;

import ma.alten.backend.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<UserEntity,Long> {

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM UserEntity u " +
            "WHERE u.email= ?1")
    Boolean selectExistsEmail(String email);
    Optional<UserEntity> findByEmail(String email);
}
