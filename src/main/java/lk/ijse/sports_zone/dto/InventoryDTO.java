package lk.ijse.sports_zone.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class Inventory {
    private String itemCode;
    private String itemName;
    private String category;
    private String brand;
    private Double unitPrice;
    private Integer qtyOnHand;
}
