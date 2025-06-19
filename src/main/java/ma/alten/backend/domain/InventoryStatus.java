package ma.alten.backend.domain;

import java.util.Arrays;
import java.util.Optional;

public enum InventoryStatus {
    INSTOCK("In Stock"),
    LOWSTOCK("Low Stock"),
    OUTOFSTOCK("Out of Stock");

    private final String libelle;

    InventoryStatus(String libelle) {
        this.libelle = libelle;
    }

    public static Optional<InventoryStatus> fromLibelle(String libelle) {
        return Arrays.stream(values())
                .filter(type -> type.libelle.equalsIgnoreCase(libelle))
                .findFirst();
    }
    public String getLibelle() {
        return libelle;
    }
}
