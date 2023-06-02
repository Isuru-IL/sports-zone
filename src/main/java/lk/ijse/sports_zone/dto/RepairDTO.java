package lk.ijse.sports_zone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Repair {
    private String repairId;
    private String custId;
    private String repairItem;
    private String date;
    private Double price;
}
