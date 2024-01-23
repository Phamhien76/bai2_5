package ra.bai2.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
public class ProductRequest {
    private String id;
    @NotNull(message = "Tên sản phẩm không được null")
    private String name;
    @NotNull(message = "Giá sản phẩm không được null")
    @Digits(integer = 3,fraction = 2,message = "Không đúng định dạng giá")
    private float price;
   @NotBlank(message = "Tiêu đề không được trống")
    private String title;
    @NotEmpty(message = "mô tả không được bỏ trông")
    private String description;
    private long catalogId;
    private boolean status;
}
