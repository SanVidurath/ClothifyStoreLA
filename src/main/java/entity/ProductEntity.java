package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductEntity {
    private Integer code;
    private String description;
    private String category;
    private String size;
    private Double unitPrice;
    private Integer quantityInStock;
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
