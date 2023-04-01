package lk.ijse.sports_zone.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CashierOrderTM {
    private String orderId;
    private String cudId;
    private Double payment;
    private String time;
    private String date;
    private String deliveryStatus;
}
