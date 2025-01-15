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
@Table(name = "panier_table")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Panier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private UserEntity user;

    @OneToMany(mappedBy = "panier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PanierItem> items = new ArrayList<>();

    public Panier(UserEntity user) {
        this.user = user;
    }

    public void addProduct(Product product, Integer quantity) {
        PanierItem panierItem = findPanierItem(product);
        if (panierItem == null) {
            panierItem = new PanierItem(this, product, quantity);
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
