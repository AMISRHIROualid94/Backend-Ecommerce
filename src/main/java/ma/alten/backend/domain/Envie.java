package ma.alten.backend.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.alten.backend.user.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "envie_table")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Envie {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "GEN_SEQ_ENVIE")
    @SequenceGenerator(sequenceName = "SEQ_ENVIE",name = "GEN_SEQ_ENVIE",initialValue = 1)
    private Long id;

    @ManyToOne
    private UserEntity user;

    @ManyToMany
    private List<Product> products = new ArrayList<>();
}

