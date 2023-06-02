package lk.ijse.sports_zone.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Customer {
    private String custId;
    private String custName;
    private String contactNo;
    private String address;
    private String email;
}
