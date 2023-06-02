package lk.ijse.sports_zone.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class User {
    private String userName;
    private String empId;
    private String password;
    private String email;
    private String jobTitle;
}
