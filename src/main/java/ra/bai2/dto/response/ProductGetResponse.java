package ra.bai2.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ra.bai2.model.Categories;

@AllArgsConstructor
@Data
@Builder
public class ProductGetResponse {
    private String id;
    private String name;
    private float price;
    private String title;
    private String description;
    private long catalogId;
    private String catalogName;
    private boolean status;
}
