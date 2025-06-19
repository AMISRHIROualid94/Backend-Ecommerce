package ma.alten.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.alten.backend.user.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "panier_table")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Panier {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "GEN_SEQ_PANIER")
    @SequenceGenerator(sequenceName = "SEQ_PANIER",name = "GEN_SEQ_PANIER",initialValue = 1)
    @Column(name = "panier_id")
    private Long id;

    @OneToOne
    @Column(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "panier", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<PanierItem> items = new ArrayList<>();

}
