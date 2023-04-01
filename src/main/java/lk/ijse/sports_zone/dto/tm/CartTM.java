package lk.ijse.sports_zone.dto.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class CartTM {
    private String code;
    private String name;
    private String catagory;
    private Integer qty;
    private Double unitPrice;
    private Double total;
    private String delivery;
    private Button btnAction;
}
