package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDetailEntity {
    private Integer orderId;
    private Integer productCode;
    private Double unitPrice;
    private Integer quantityPurchased;
}
