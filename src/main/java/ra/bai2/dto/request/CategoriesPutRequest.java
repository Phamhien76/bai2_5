package ra.bai2.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
public class CategoriesPutRequest {
    private long id;
    @NotNull(message = "Tên danh mục không được null")
    private String name;
    private int priority;
    private String description;
    private boolean status;
}
