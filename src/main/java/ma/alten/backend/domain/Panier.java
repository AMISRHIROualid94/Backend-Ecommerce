package ma.alten.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "panier_table")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Panier {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "GEN_SEQ_PANIER")
    @SequenceGenerator(sequenceName = "SEQ_PANIER",name = "GEN_SEQ_PANIER",initialValue = 1)
    @Column(name = "panier_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "panier", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<PanierItem> items = new ArrayList<>();

    public void addProduct(Product product, Integer quantity) {
        PanierItem panierItem = findPanierItem(product);
        if (panierItem == null) {
            panierItem = PanierItem.builder()
                    .panier(this)
                    .product(product)
                    .quantity(quantity)
                    .build();
            items.add(panierItem);
        } else {
            panierItem.setQuantity(panierItem.getQuantity() + quantity);
        }
    }

    public void removeProduct(Product product) {
        PanierItem panierItem = findPanierItem(product);
        if (panierItem != null) {
            items.remove(panierItem);
        }
    }

    private PanierItem findPanierItem(Product product) {
        return items.stream()
                .filter(panierItem -> panierItem.getProduct().equals(product))
                .findFirst()
                .orElse(null);
    }
}
