package ma.alten.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(name = "envie_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "envie_product",
            joinColumns = @JoinColumn(name = "envie_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    @JsonIgnore
    private List<Product> products = new ArrayList<>();
}

