package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "orderdetail")
public class OrderDetailEntity {
    private Integer orderId;
    private Integer productCode;
    private Double unitPrice;
    private Integer quantityPurchased;
}
