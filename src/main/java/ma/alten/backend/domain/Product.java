package ma.alten.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "produit_table")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "GEN_SEQ_PRODUCT")
    @SequenceGenerator(sequenceName = "SEQ_product",name = "GEN_SEQ_PRODUCT",initialValue = 1)
    private Long id;
    private String code;
    private String name;
    private String description;
    private String image;
    private String category;
    private Double price;
    private Integer quantity;
    private String internalReference;
    private Integer shellId;
    @Enumerated(EnumType.STRING)
    private InventoryStatus inventoryStatus;
    private Integer rating;
    private Date createdAt;
    private Date updatedAt;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<PanierItem> panierItems;
}
