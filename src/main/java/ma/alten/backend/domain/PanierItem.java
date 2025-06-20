package ma.alten.backend.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "panier_item_table")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PanierItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "GEN_SEQ_PANIER_ITEM")
    @SequenceGenerator(sequenceName = "SEQ_PANIER_ITEM",name = "GEN_SEQ_PANIER_ITEM",initialValue = 1)
    @Column(name = "panier_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "panier_id")
    private Panier panier;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantite")
    private Integer quantity;
}

