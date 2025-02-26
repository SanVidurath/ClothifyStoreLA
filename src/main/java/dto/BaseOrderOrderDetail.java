package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BaseOrderOrderDetail {
    private Integer orderId;
    private Integer productCode;
    private Integer employeeId;
    private String employeeName;
    private Integer customerId;
    private Double unitPrice;
    private String date;
    private Integer quantity;
    private String paymentType;
}
