package ra.bai2.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@AllArgsConstructor
@Data
@Builder
public class UserPostRequest {
    @NotNull(message = "UserName không được null, vui lòng nhập lại")
    private String name;
    @NotEmpty(message = "Email không được bỏ trống")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$",message = "Email không đúng định dạng")
    private String email;
    @Length(min = 6, max = 10, message = "Password phải trên 6 ký tự & ít nhất phải 8 ký tự")
    private String password;
    private Date created;
    @Pattern(regexp = "^(070|080|090)-\\d{4}-\\d{4}$",message = "Không đúng định dạng số điện thoại")
    private String phone;
    private boolean permission;
    private boolean status;
}

