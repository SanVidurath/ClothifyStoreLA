package entity;

import dto.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
