package lk.ijse.sports_zone.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class RepairTM {
    private String repairId;
    private String custId;
    private String repairitem;
    private String date;
    private Double price;
}
