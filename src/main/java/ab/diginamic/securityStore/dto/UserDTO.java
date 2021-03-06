package ab.diginamic.securityStore.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO {

    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private String email;

}
