package ma.alten.backend.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.alten.backend.domain.Envie;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "user_entity")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "GEN_SEQ_USER")
    @SequenceGenerator(sequenceName = "SEQ_USER",name = "GEN_SEQ_USER")
    private Long id;
    private String userName;
    private String firstName;
    @Column(unique = true)
    private String email;
    @OneToMany(mappedBy = "user")
    private List<Envie> envieList =new ArrayList<>();
    private byte[] storedHash;
    private byte[] storedSalt;

}
