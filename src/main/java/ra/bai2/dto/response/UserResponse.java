package ra.bai2.dto.response;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@AllArgsConstructor
@Data
public class UserResponse {
    private long id;
    private String name;
    private String email;
    private String password;
    private Date created;
    private String phone;
    private boolean status;
}
