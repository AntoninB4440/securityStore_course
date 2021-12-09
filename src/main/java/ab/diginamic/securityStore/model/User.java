package ab.diginamic.securityStore.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="users")
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    private String username;
    private String password;
    private String role;
    private int enabled;

    private String firstname;
    private String lastname;
    private String email;

}
