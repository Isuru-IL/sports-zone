package lk.ijse.sports_zone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SupplyLoadDetailDTO {
    private String itemCode;
    private Double totalPrice;
    private Integer qty;
}
