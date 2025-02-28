package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer code;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String size;

    @Column(nullable = false, name = "unit_price")
    private Double unitPrice;

    @Column(nullable = false, name = "quantity")
    private Integer quantityInStock;

    @Column(nullable = false, name = "supplier_id")
    private Integer supplierId;

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass())
            return false;

        ProductEntity productEntity = (ProductEntity) object;
        return (productEntity.description.equals(this.description)
                && productEntity.category.equals(this.category)
                && productEntity.size.equals(this.size)
        && productEntity.supplierId.equals(this.supplierId));
    }

    @Override
    public int hashCode(){
        return Objects.hash(description,category,size,supplierId);
    }
}
