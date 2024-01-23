package ra.bai2.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ra.bai2.model.Categories;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductPostResponse {
    private String id;
    private String name;
    private float price;
    private String title;
    private String description;
    private Date created;
    private boolean status;
}
