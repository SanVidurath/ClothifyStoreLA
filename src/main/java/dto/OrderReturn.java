package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderReturn {
    private Integer orderId;
    private Integer productCode;
    private Integer quantityReturned;
    private String date;
}
