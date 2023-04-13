package lk.ijse.sports_zone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {
    private String deliveryId;
    private String empId;
    private String orderId;
    private String vehiId;
    private String location;
    private String deliveryDate;
    private String dueDate;
    private String deliveryStaus;
}
