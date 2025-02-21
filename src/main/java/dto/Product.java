package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
    private Integer code;
    private String description;
    private String category;
    private String size;
    private Double unitPrice;
    private Integer quantityInStock;
    private Integer supplierId;
}
