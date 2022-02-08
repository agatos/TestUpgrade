package upgrade.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UsersDTO implements Serializable {

    private int id;
    private String firstname;
    private String lastname;
    private String username;
    private Date createdAt;
    private Date updatedAt;

}
