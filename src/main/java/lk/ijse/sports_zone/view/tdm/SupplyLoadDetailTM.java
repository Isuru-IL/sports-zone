package lk.ijse.sports_zone.dto.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SupplyLoadDetailTM {
    private String itemCode;
    private String itemName;
    private String catagory;
    private Integer qty;
    private Double buyUnitPrice;
    private Double total;
    private Button btnAction;
}
