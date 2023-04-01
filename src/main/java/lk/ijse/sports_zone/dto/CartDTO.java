package lk.ijse.sports_zone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartDTO {
    private String itemCode;
    private Integer qty;
    private Double netTotal;
    private String deliveryStatus;
}
