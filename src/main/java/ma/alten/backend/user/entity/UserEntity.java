package ma.alten.backend.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "email", unique = true)
    private String email;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, mappedBy = "user")
    @JsonIgnore
    private List<Envie> envieList =new ArrayList<>();

    @Column(name = "hash")
    private byte[] storedHash;

    @Column(name = "salt")
    private byte[] storedSalt;

}
