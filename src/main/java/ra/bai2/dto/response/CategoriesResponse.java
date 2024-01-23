package ra.bai2.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoriesResponse {
    private long id;
    private String name;
    private int priority;
    private String description;
    private boolean status;
}
