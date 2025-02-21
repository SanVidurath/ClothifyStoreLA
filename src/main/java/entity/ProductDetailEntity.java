package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDetailEntity {
    private Integer prodCode;
    private Integer supId;
    private Double unitPrice;
    private Integer qtySupplied;
}
