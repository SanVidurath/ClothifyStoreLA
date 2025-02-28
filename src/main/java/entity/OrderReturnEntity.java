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
@Table(name = "orderreturns")
public class OrderReturnEntity {
    private Integer orderId;
    private Integer productCode;
    private Integer quantityReturned;
    private String date;
}
