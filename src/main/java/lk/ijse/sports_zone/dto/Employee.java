package lk.ijse.sports_zone.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class Employee {
    private String empId;
    private String empName;
    private String address;
    private String dob;
    private String contactNo;
    private Double salary;
    private String email;
    private String nic;
    private String jobTitle = "Cashier";
}
