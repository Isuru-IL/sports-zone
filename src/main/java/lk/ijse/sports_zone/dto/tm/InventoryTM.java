package lk.ijse.sports_zone.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class InventoryTM {
    private String itemCode;
    private String itemName;
    private String category;
    private String brand;
    private Double unitPrice;
    private Integer qtyOnHand;
}
