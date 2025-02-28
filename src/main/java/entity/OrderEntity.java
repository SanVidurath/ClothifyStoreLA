package entity;

import dto.OrderDetail;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "orders")
public class OrderEntity {
    private Integer id;
    private String date;
    private Integer employeeId;
    private String employeeName;
    private Integer customerId;
    private Double total;
    private String paymentType;
    private List<OrderDetailEntity> orderDetailEntityList;
}
